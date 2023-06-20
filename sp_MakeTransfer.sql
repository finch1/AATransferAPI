ALTER TABLE transfers ADD CONSTRAINT FOREIGN KEY FK_transfers_accounts_account_from_account_id (account_from) REFERENCES accounts(accountid);
ALTER TABLE transfers ADD CONSTRAINT FOREIGN KEY FK_transfers_accounts_account_to_account_id (account_to) REFERENCES accounts(accountid);

DELIMITER //

DROP PROCEDURE IF EXISTS sp_MakeTransfer//

CREATE PROCEDURE sp_MakeTransfer (IN ext_account_from bigint,
                                  IN ext_account_to bigint,
                                  IN ext_amount double,
                                  IN ext_transfer_ref varchar(255),
                                  IN ext_created_on datetime(6),
                                  OUT message varchar(200))

sp:  BEGIN

    DECLARE account_from bigint;
    DECLARE balance_from double; # set this with select and use for update to confirm amount did not change by another transaction
    DECLARE account_to bigint;
    DECLARE balance_to double;
    DECLARE currency_from varchar(10);

    # prepare message if transaction (any of the below queries) fail
    SET message = 'Transfer unsuccessful!';

      START TRANSACTION;
        Select accountid, currency, balance into account_from, currency_from, balance_from
        from accounts
        where accountid = ext_account_from
        and balance >= ext_amount
        and status = 0 # ACTIVE
        FOR UPDATE;

        IF account_from IS NULL THEN
            SET message = 'From account not valid for transfer.';
            ROLLBACK; # to cancel select for update
            LEAVE sp;
        END IF;

        Select accountid, balance into account_to, balance_to
        from accounts
        where accountid = ext_account_to
        and status = 0 # ACTIVE
        and currency = currency_from
        FOR UPDATE;

        IF account_to IS NULL THEN
            SET message = 'To account not valid for transfer.';
            ROLLBACK;
            LEAVE sp;
        END IF;

        update accounts set balance = balance - ext_amount where accountid = account_from and balance = balance_from;
        update accounts set balance = balance + ext_amount where accountid = account_to and balance = balance_to;
        insert into transfers(transferid, account_from, account_to, amount, currency, transfer_ref, created_on)
                    values (UUID_TO_BIN(UUID()), ext_account_from, ext_account_to, ext_amount, currency_from, ext_transfer_ref, ext_created_on);
    COMMIT;

    SET message = 'Funds successfully transferred.';

END //

###

DROP PROCEDURE IF EXISTS sp_TransferForUser//

CREATE PROCEDURE sp_TransferForUser (IN ext_user bigint)

BEGIN

    # Sets a shared mode lock on any rows that are read. Other sessions can read the rows, but cannot modify them until your transaction commits. If any of these rows were changed by another transaction that has not yet committed, your query waits until that transaction ends and then uses the latest values. 
    START TRANSACTION;

    (select * 
        from transfers
        where account_from in (select accountid from accounts where userid = ext_user) LOCK IN SHARE MODE)

    union all

    (select * 
        from transfers
        where account_to in (select accountid from accounts where userid = ext_user) LOCK IN SHARE MODE);

    COMMIT;
END //

###

DROP PROCEDURE IF EXISTS sp_TransferForAccount//

CREATE PROCEDURE sp_TransferForAccount (IN ext_account bigint)

BEGIN

    # Sets a shared mode lock on any rows that are read. Other sessions can read the rows, but cannot modify them until your transaction commits. If any of these rows were changed by another transaction that has not yet committed, your query waits until that transaction ends and then uses the latest values. 
    START TRANSACTION;

        (select * 
            from transfers
            where account_from = ext_account LOCK IN SHARE MODE)

        union all

        (select * 
            from transfers
            where account_to = ext_account LOCK IN SHARE MODE);

    COMMIT;

END //
#DELIMITER ;
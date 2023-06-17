-- create
CREATE TABLE accounts (
  accountid bigint PRIMARY KEY,
  balance double NOT NULL,
  status tinyint NOT NULL,
  userid bigint NOT NULL
);


insert into accounts values (1, 100.0, 0, 1);
insert into accounts values (2, 100.0, 0, 2);


select * from accounts;

CREATE TABLE transfers (
  transferid binary(16) PRIMARY KEY,
  account_from bigint NOT NULL,
  account_to   bigint NOT NULL,
  amount       double NOT NULL,
  currency     varbinary(255) NOT NULL,
  transfer_ref varchar(255) NOT NULL,
  created_on datetime(6) NOT NULL
);


DELIMITER //
CREATE PROCEDURE sp_MakeTransfer (IN ext_account_from bigint,
                                  IN ext_account_to bigint,
                                  IN ext_amount double,
                                  IN ext_currency varchar(10),
                                  IN ext_transfer_ref varchar(255),
                                  IN ext_created_on datetime(6),
                                  OUT message varchar(200))

sp:  BEGIN

      DECLARE account_from bigint;
      DECLARE account_to bigint;

      Select accountid into account_from
      from accounts where accountid = ext_account_from and balance >= ext_amount and status = 0;

      IF account_from IS NULL THEN
          SET message = 'from account not valid for transfer';
          LEAVE sp;
      END IF;

      Select accountid into account_to
      from accounts where accountid = ext_account_to and status = 0;

      IF account_to IS NULL THEN
          SET message = 'to account not valid for transfer';
          LEAVE sp;
      END IF;

      # prepare message if transaction fails
      SET message = 'transfer unsuccessful';

      START TRANSACTION;
      update accounts set balance = balance - ext_amount where accountid = account_from;
      update accounts set balance = balance + ext_amount where accountid = account_to;
      insert into transfers(transferid, account_from, account_to, amount, currency, transfer_ref, created_on)
                  values (UUID_TO_BIN(UUID()), ext_account_from, ext_account_to, ext_amount, ext_currency, ext_transfer_ref, ext_created_on);

      SET message = 'funds successfully transferred';
      COMMIT;

END //
#DELIMITER ;

CALL sp_MakeTransfer(1,2, 99.0, 'EUR', 'test', '2023-06-17 22:01:10.741714', @Return);

select @Return;

select * from transfers;
select * from accounts;
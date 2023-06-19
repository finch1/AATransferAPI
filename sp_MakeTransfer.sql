-- create
CREATE TABLE accounts (
  accountid bigint PRIMARY KEY,
  balance double NOT NULL,
  status tinyint NOT NULL,
  userid bigint NOT NULL,
  currency varchar(255) NOT NULL
);


insert into accounts values (1, 100.0, 0, 1, 'EUR');
insert into accounts values (2, 100.0, 0, 2, 'EUR');


select * from accounts;

CREATE TABLE transfers (
  transferid binary(16) PRIMARY KEY,
  account_from bigint NOT NULL,
  account_to   bigint NOT NULL,
  amount       double NOT NULL,
  currency     varchar(255) NOT NULL,
  transfer_ref varchar(255) NOT NULL,
  created_on datetime(6) NOT NULL
);


DELIMITER //
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

      START TRANSACTION;
        Select accountid, currency, balance into account_from, currency_from, balance_from
        from accounts
        where accountid = ext_account_from
        and balance >= ext_amount
        and status = 0
        FOR UPDATE;

        IF account_from IS NULL THEN
            SET message = 'from account not valid for transfer';
            ROLLBACK; # to cancel select for update
            LEAVE sp;
        END IF;

        Select accountid, balance into account_to, balance_to
        from accounts
        where accountid = ext_account_to
        and status = 0
        and currency = currency_from
        FOR UPDATE;

        IF account_to IS NULL THEN
            SET message = 'to account not valid for transfer';
            ROLLBACK;
            LEAVE sp;
        END IF;

        # prepare message if transaction fails
        SET message = 'transfer unsuccessful';


        update accounts set balance = balance - ext_amount where accountid = account_from and balance = balance_from;
        update accounts set balance = balance + ext_amount where accountid = account_to and balance = balance_to;
        insert into transfers(transferid, account_from, account_to, amount, currency, transfer_ref, created_on)
                    values (UUID_TO_BIN(UUID()), ext_account_from, ext_account_to, ext_amount, currency_from, ext_transfer_ref, ext_created_on);

        SET message = 'funds successfully transferred';
      COMMIT;

END //
#DELIMITER ;

CALL sp_MakeTransfer(1,2, 99.0, 'test', '2023-06-17 22:01:10.741714', @Return);

select @Return;

select * from transfers;
select * from accounts;


-------------------------------------

DELIMITER $$
CREATE PROCEDURE GetEmtyStatus(
    OUT Message  VARCHAR(100))
BEGIN
    DECLARE sum_null DECIMAL(10,2) DEFAULT 0;
    SELECT COUNT(*) INTO sum_null FROM TASKS WHERE status IS NULL;
    IF sum_null > 0 THEN
        SET Message = 'We have rows with NULL!!!';
    END IF;
END$$

-------------------------------------


DELIMITER //
CREATE PROCEDURE myProc()
BEGIN
        DECLARE my_variable VARCHAR(200);
        BEGIN
                SET my_variable='This value was set in the inner block';
        END;
        SELECT my_variable, 'Can''t see changes made in the inner block.';
END //


CALL myProc();

-------------------------------------


create table Student_detail(
S_Studentid INT primary key, 
S_StudentName Varchar(20), 
S_Address Varchar(20)
);

delimiter //
create Procedure Insert_Studentdetails4(S_Studentid INT, 
                                        S_StudentName Varchar(20), 
                                        S_Address Varchar(20),
                                        OUT got_error Varchar(20))
BEGIN

  
  DECLARE EXIT HANDLER FOR 1062 SET got_error='insert error';
  INSERT INTO Student_detail
  Values(S_Studentid,S_StudentName,S_Address);
END //

call Insert_Studentdetails4(1, 'a', 'a', @e);
select @e;
call Insert_Studentdetails4(1, 'a', 'a', @e);
select @e;


      if account_from is NULL
        SET return = 'from account not valid for transfer';
      end if;
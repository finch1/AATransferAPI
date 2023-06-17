-- create
CREATE TABLE account (
  accountid bigint PRIMARY KEY,
  balance double NOT NULL,
  status tinyint NOT NULL,
  userid bigint NOT NULL
);


insert into account values (1, 100.0, 0, 1);
insert into account values (2, 100.0, 0, 2);


select * from account;

CREATE TABLE transfers (
  transferid bigint PRIMARY KEY,
  account_from bigint NOT NULL,
  account_to   bigint NOT NULL,
  amount       double NOT NULL,
  currency     varbinary(255) NOT NULL,
  transfer_ref varchar(255) NOT NULL
);



DELIMITER //
CREATE PROCEDURE sp_MakeTransfer (IN ext_account_from bigint,
                                  IN ext_account_to bigint,
                                  IN ext_balance double,
                                  OUT message varchar(200))

sp:  BEGIN

      DECLARE account_from bigint;
      DECLARE account_to bigint;

      Select accountid into account_from
      from account where accountid = ext_account_from and balance >= ext_balance and status = 0;

      IF account_from IS NULL THEN
          SET message = 'from account not valid for transfer';
          LEAVE sp;
      END IF;

      Select accountid into account_to
      from account where accountid = ext_account_to and status = 0;

      IF account_to IS NULL THEN
          SET message = 'to account not valid for transfer';
          LEAVE sp;
      END IF;


      update account set balance = balance - ext_balance where accountid = account_from;
      update account set balance = balance + ext_balance where accountid = account_to;
      insert into transfers values (1, account_from, account_to, ext_balance, 'EUR', 'test');

      SET message = 'funds successfully transfered';

END //
#DELIMITER ;

CALL sp_MakeTransfer(1,2, 99.0, @Return);

select @Return;

select * from transfers;
select * from account;
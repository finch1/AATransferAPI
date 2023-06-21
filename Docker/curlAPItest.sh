#!/bin/sh
RED=`tput setaf 1`
YEL=`tput setaf 3`
RES=`tput sgr0`
echo "${RED}Press enter after every test to continue."

echo "${YEL}Create New Account-User 1${RES}"
read
curl -v -XPOST -H "Content-type: application/json" -d '{
    "userID":1,
    "currency":"EUR",
    "balance":100.0
}' 'localhost:8080/api/v1/accounts/save' | jq

echo "${YEL}Create New Account-User 2${RES}"
read
curl -v -XPOST -H "Content-type: application/json" -d '{
    "userID":2,
    "currency":"EUR",
    "balance":100.0
}' 'localhost:8080/api/v1/accounts/save' | jq

echo "${YEL}Create New Account-User 2${RES}"
read
curl -v -XPOST -H "Content-type: application/json" -d '{
    "userID":2,
    "currency":"EUR",
    "balance":100.0
}' 'localhost:8080/api/v1/accounts/save' | jq

echo "${YEL}Create New Account-User 2${RED}-Bad Request${RES}"
read
curl -v -XPOST -H "Content-type: application/json" -d '{
    "userID":2,
    "currency":"EUR"
}' 'localhost:8080/api/v1/accounts/save' | jq


echo "${YEL}Get all accounts${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts' | jq


echo "${YEL}Get one account by account ID = 1${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/1' | jq

echo "${YEL}Get one account by account ID = 10${RED}-Bad Request${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/10' | jq


echo "${YEL}Get all accounts for user = 2${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/user/2' | jq

echo "${YEL}Get all accounts for user = 2${RED}-Bad Request${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/user/10' | jq


echo "${YEL}Update Account = Block Account 1${RES}"
read
curl -v -XPATCH -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/block?id=1' | jq

echo "${YEL}Update Account = Block Account 10${RED}-Bad Request${RES}"
read
curl -v -XPATCH -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/block?id=10' | jq


echo "${YEL}Update Account = Reactivate Account 1${RES}"
read
curl -v -XPATCH -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/reactivate?id=1' | jq

echo "${YEL}Update Account = Reactivate${RED}-Bad Request${RES}"
read
curl -v -XPATCH -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/reactivate' | jq


echo "${YEL}Update Account = Close Account 3${RES}"
read
curl -v -XPATCH -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/close?id=3' | jq

echo "${YEL}Update Account = Close${RED}-Bad Request${RES}"
read
curl -v -XPATCH -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/close?id=10' | jq


echo "${YEL}Confirm account updates${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts' | jq


echo "${YEL}Create payment transfer, from account 1 to account 2, amount 53.0 ${RES}"
read
curl -v -XPOST -H "Content-type: application/json" -d '{
    "transferRef":"test transfer",
    "accountFrom":1,
    "accountTo":2,
    "amount":53.0
}' 'localhost:8080/api/v1/transfers/makeTransfer' | jq

echo "${YEL}Verify Payment, from account 1 to account 2, amount 53.0 ${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/1' | jq
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/2' | jq


echo "${YEL}Create Payment, from account 3 to account 4 (4 does not exist)${RED}-Bad Request${RES}"
read
curl -v -XPOST -H "Content-type: application/json" -d '{
    "transferRef":"test transfer",
    "accountFrom":3,
    "accountTo":4
}' 'localhost:8080/api/v1/transfers/makeTransfer' | jq


echo "${YEL}Get all transfers${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/transfers' | jq

echo "${YEL}Get all transfers${RED}-Bad Request${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/transfers/' | jq


echo "${YEL}Get all transfers for account = 1${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/transfers/account/1' | jq

echo "${YEL}Get all transfers for account = 1${RED}-Bad Request${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/transfers/account/10' | jq


echo "${YEL}Get all transfers for user = 1${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/transfers/user/1' | jq

echo "${YEL}Get all transfers for user = 1${RED}-Bad Request${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/transfers/user/10' | jq



echo "${YEL}Update account = 1 status = close and try payment transfer, from account 1 to account 2, amount 5.0 ${RES}"
read
curl -v -XPATCH -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/close?id=1' | jq
curl -v -XPOST -H "Content-type: application/json" -d '{
    "transferRef":"test transfer",
    "accountFrom":1,
    "accountTo":2,
    "amount":5.0
}' 'localhost:8080/api/v1/transfers/makeTransfer' | jq

echo "${YEL}Verify Payment: no funds transferred from account 1 to account 2${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/1' | jq
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/2' | jq

echo "${YEL}Try payment transfer from account 2 (active) to account 3(closed), amount =5 ${RES}"
read
curl -v -XPOST -H "Content-type: application/json" -d '{
    "transferRef":"test transfer",
    "accountFrom":2,
    "accountTo":3,
    "amount":5.0
}' 'localhost:8080/api/v1/transfers/makeTransfer' | jq

echo "${YEL}Verify Payment: no funds transferred from account 2 to account 3${RES}"
read
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/2' | jq
curl -v -XGET -H "Content-type: application/json" 'localhost:8080/api/v1/accounts/account/3' | jq
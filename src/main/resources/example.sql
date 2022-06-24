CREATE DATABASE IF NOT EXISTS BANKEXAMPLEDB;

CREATE TABLE Users (
userId INTEGER (10) AUTOINCREMENT PRIMARY KEY NOT NULL,
name VARCHAR (50) NOT NULL,
address VARCHAR (255)
);

CREATE TABLE Accounts (
accountId INTEGER (10) AUTOINCREMENT PRIMARY KEY NOT NULL,
userId INTEGER (10) NOT NULL,
balance INTEGER (15),
currency VARCHAR (10),
FOREIGN KEY (userId) REFERENCES Users(userID),
UNIQUE (userId, currency)
);

CREATE TABLE Transactions (
transactionId INTEGER (10) AUTOINCREMENT PRIMARY KEY NOT NULL,
accountId INTEGER (10) NOT NULL,
amount INTEGER (15) NOT NULL,
FOREIGN KEY (accountId) REFERENCES Accounts(accountId)
);
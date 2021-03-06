CREATE TABLE Users (
                       userId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                       name VARCHAR (50) NOT NULL,
                       address VARCHAR (255)
);

CREATE TABLE Accounts (
                          accountId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                          userId INTEGER (10) NOT NULL,
                          balance DECIMAL (15, 3),
                          currency VARCHAR (10) NOT NULL,
                          FOREIGN KEY (userId) REFERENCES Users(userID),
                          UNIQUE (userId, currency)
);

CREATE TABLE Transactions (
                              transactionId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                              accountId INTEGER (10) NOT NULL,
                              amount DECIMAL (15, 3) NOT NULL,
                              FOREIGN KEY (accountId) REFERENCES Accounts(accountId)
);

package by.it_academy.query;

import by.it_academy.model.Account;
import by.it_academy.model.Transaction;

import java.sql.*;

import static java.lang.String.format;

public class AccountQueryExecutor {

    public static void addAccountForUserToDb(Account account, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format("INSERT INTO Accounts (userId, balance, currency) VALUES('%d','%d', '%s')",
                account.getUserId(), account.getBalance(), account.getCurrency()));
        statement.close();
    }

    public static void changeBalanceOfAccount(Transaction transaction, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(format("SELECT * FROM Accounts WHERE accountId = '%d'",
                transaction.getAccountId()));
        int newBalance = resultSet.getInt("balance") + transaction.getAmount();
        statement.executeUpdate(format("UPDATE Accounts SET balance = '%d' WHERE accountId = '%d'",
                newBalance, transaction.getAccountId()));
        resultSet.close();
        statement.close();
    }
}

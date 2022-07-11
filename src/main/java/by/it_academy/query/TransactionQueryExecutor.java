package by.it_academy.query;

import by.it_academy.model.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.String.format;

public class TransactionQueryExecutor {

    private static final int MAX_VALUE_ON_ACCOUNT = 2_000_000_000;

    public void addTransactionToDb(Transaction transaction, Connection connection) throws SQLException {

        String sqlSelectAccountBalance = format("SELECT * FROM Accounts WHERE accountId = '%d'",
                transaction.getAccountId());

        Statement statement = connection.createStatement();
        ResultSet resultSetAccounts = statement.executeQuery(format("SELECT * FROM Accounts WHERE userId = '%d'",
                transaction.getAccountId()));
        if (!resultSetAccounts.next()) {
            throw new SQLException("Failure! No such accountID found");
        }

        ResultSet resultSetBalance = statement.executeQuery(sqlSelectAccountBalance);
        int currentBalance = resultSetBalance.getInt("balance");

        try {
            if (currentBalance + transaction.getAmount() >= MAX_VALUE_ON_ACCOUNT) {
                throw new SQLException("Balance cannot exceed 2b");
            } else if (currentBalance + transaction.getAmount() < 0) {
                throw new SQLException("Balance cannot be less 0");
            } else {
                statement.executeUpdate(format("INSERT INTO Transactions (accountId, amount) VALUES('%d','%f')",
                        transaction.getAccountId(), transaction.getAmount()));
                statement.executeUpdate(format("UPDATE Accounts SET balance = " +
                                "(SELECT balance FROM Accounts WHERE accountId = '%d') + ('%f') WHERE accountId = '%d'",
                        transaction.getAccountId(), transaction.getAmount(), transaction.getAccountId()));
                System.out.println("Success!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        statement.close();
    }
}

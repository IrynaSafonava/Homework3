package by.it_academy.query;

import by.it_academy.model.Transaction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.lang.String.format;

public class TransactionQueryExecutor {

    public void addTransactionToDb(Transaction transaction, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        String sqlSelectAccountBalance = format("SELECT * FROM Accounts WHERE accountId = '%d'",
                transaction.getAccountId());

        ResultSet resultSet = statement.executeQuery(sqlSelectAccountBalance);
        int currentBalance = resultSet.getInt("balance");

        statement = connection.createStatement();
        resultSet = statement.executeQuery(format("SELECT * FROM Accounts WHERE userId = '%d'",
                transaction.getAccountId()));
        if(!resultSet.next()) {
            throw new SQLException("Failure! No such accountID found");
        }

        try {
            if (currentBalance + transaction.getAmount() >= 2_000_000_000) {
                throw new SQLException("Balance cannot exceed 2b");
            } else if (currentBalance + transaction.getAmount() < 0) {
                throw new SQLException("Balance cannot be less 0");
            } else {
                statement.executeUpdate(format("INSERT INTO Transactions (accountId, amount) VALUES('%d','%d')",
                        transaction.getAccountId(), transaction.getAmount()));
                statement.executeUpdate(format("UPDATE Accounts SET balance = " +
                                "(SELECT balance FROM Accounts WHERE accountId = '%d') + ('%d') WHERE accountId = '%d'",
                        transaction.getAccountId(), transaction.getAmount(), transaction.getAccountId()));
                System.out.println("Success!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        resultSet.close();
        statement.close();
    }
}

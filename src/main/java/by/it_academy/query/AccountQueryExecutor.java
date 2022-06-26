package by.it_academy.query;

import by.it_academy.model.Account;
import by.it_academy.model.Transaction;

import java.sql.*;

import static java.lang.String.format;

public class AccountQueryExecutor {

    public void addAccountForUserToDb(Account account, Connection connection) throws SQLException {

        String sqlInsertAccount = format("INSERT INTO Accounts (userId, balance, currency) VALUES('%d', '%d', '%s')",
                account.getUserId(), account.getBalance(), account.getCurrency());

        try (PreparedStatement statement = connection
                .prepareStatement(sqlInsertAccount, Statement.RETURN_GENERATED_KEYS)) {
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setAccountId(generatedKeys.getInt(1));
                    System.out.println("Account successfully created with ID " + account.getAccountId());
                } else {
                    throw new SQLException("Creating an account failed. No Id obtained");
                }
            }
        }
    }

    public void changeBalanceOfAccount(Transaction transaction, Connection connection)
            throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format("UPDATE Accounts SET balance = " +
                        "(SELECT balance FROM Accounts WHERE accountId = '%d') + ('%d') WHERE accountId = '%d'",
                transaction.getAccountId(), transaction.getAmount(), transaction.getAccountId()));
        statement.close();
    }
}

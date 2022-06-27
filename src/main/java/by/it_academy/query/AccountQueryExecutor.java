package by.it_academy.query;

import by.it_academy.model.Account;

import java.sql.*;

import static java.lang.String.format;

public class AccountQueryExecutor {

    public void addAccountForUserToDb(Account account, Connection connection) throws SQLException {

        String sqlInsertAccount = format("INSERT INTO Accounts (userId, balance, currency) VALUES('%d', '%f', '%s')",
                account.getUserId(), account.getBalance(), account.getCurrency());

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(format("SELECT * FROM Users WHERE userId = '%d'",
                account.getUserId()));
        if (!resultSet.next()) {
            throw new SQLException("Failure! No such userID found");
        }
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(sqlInsertAccount, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setAccountId(generatedKeys.getInt(1));
                    System.out.println("Account successfully created with ID " + account.getAccountId());
                } else {
                    throw new SQLException("Creating an account failed. No Id obtained");
                }
            }
        }
        statement.close();
    }
}
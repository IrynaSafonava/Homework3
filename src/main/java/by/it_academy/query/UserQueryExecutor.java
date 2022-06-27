package by.it_academy.query;

import by.it_academy.model.*;

import java.sql.*;

import static java.lang.String.format;

public class UserQueryExecutor {

    public void addUserToDb(User user, Connection connection) throws SQLException {
        String sqlInsertUser = format("INSERT INTO Users (name, address) VALUES('%s', '%s')",
                user.getUserName(), user.getAddress());
        try (PreparedStatement statement = connection
                .prepareStatement(sqlInsertUser, Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failure! No rows affected!");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                    System.out.println("User successfully created with ID " + user.getUserId());
                } else {
                    throw new SQLException("Creating a user failed. No Id obtained");
                }
            }
        }
    }
}

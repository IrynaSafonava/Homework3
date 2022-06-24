package by.it_academy.query;

import by.it_academy.model.*;
import java.sql.*;
import static java.lang.String.format;

public class UserQueryExecutor {

//    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users;";

//    public static void printAllUsers(Connection connection) throws SQLException {
//        PreparedStatement statement =
//                connection.prepareStatement(SQL_FIND_ALL_USERS);
//        ResultSet resultSet = statement.executeQuery();
//        while (resultSet.next()) {
//            System.out.println('\n' + "userId: " + resultSet.getInt("userId"));
//            System.out.println("name: " + resultSet.getString("name"));
//            System.out.println("address: " + resultSet.getString("address") + '\n');
//        }
//        resultSet.close();
//        statement.close();
//    }

    public static void addUserToDb(User user, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format("INSERT INTO Users (name, address) VALUES('%s', '%s')",
                user.getUserName(), user.getAddress()));
        statement.close();
    }
}

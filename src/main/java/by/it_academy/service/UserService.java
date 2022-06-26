package by.it_academy.service;

import by.it_academy.model.User;
import by.it_academy.query.UserQueryExecutor;
import java.sql.Connection;
import java.util.Scanner;

public class UserService {

    private final UserQueryExecutor userQueryExecutor;

    public UserService(UserQueryExecutor userQueryExecutor) {
        this.userQueryExecutor = userQueryExecutor;
    }

    public void inputUserReader(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        do {
            try {
                System.out.println("Enter user's name: ");
                user.setUserName(scanner.nextLine());
                System.out.println("Enter user's address: ");
                user.setAddress(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        } while (!valid);
    }

    public User saveUser(Connection connection) {
        User user = new User();
        inputUserReader(user);
        try {
            userQueryExecutor.addUserToDb(user, connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}

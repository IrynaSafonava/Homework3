package by.it_academy.demo;

import by.it_academy.model.*;
import by.it_academy.service.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public void executeProgramme(UserService userService, AccountService accountService,
                                 TransactionService transactionService, Connection connection) throws SQLException {
        String actionCode;
        User user = new User();
        Account account = new Account();
        do {
            printMenu();
            actionCode = new Scanner(System.in).nextLine();
            switch (actionCode.charAt(0)) {
                case '1':
                    user = userService.saveUser(connection);
                    break;
                case '2':
                    int userId = user.getUserId();
                    account = accountService.saveAccount(userId, connection);
                    break;
                case '3':
                    transactionService.saveTransaction(account, connection, 1);
                    break;
                case '4':
                    transactionService.saveTransaction(account, connection, -1);
                    break;
                case '5':
                    System.out.println("Thanks for using the programme!");
                    break;
                default:
                    System.out.println("Unknown option. Please enter again");
            }
        } while (!"5".equals(actionCode));
        connection.close();
    }

    public static void printMenu() {
        System.out.println("\nPlease select an action");
        System.out.println("1 - register new user");
        System.out.println("2 - create new account");
        System.out.println("3 - increase balance");
        System.out.println("4 - decrease balance");
        System.out.println("5 - quit\n");
    }
}

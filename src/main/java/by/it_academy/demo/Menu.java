package by.it_academy.demo;

import by.it_academy.model.Account;
import by.it_academy.model.Transaction;
import by.it_academy.model.User;
import by.it_academy.service.AccountService;
import by.it_academy.service.TransactionService;
import by.it_academy.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    private UserService userService;
    private AccountService accountService;

    public Menu(UserService userService, AccountService accountService, TransactionService transactionService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    public void executeProgramme(UserService userService, AccountService accountService,
                                 TransactionService transactionService, Connection connection) throws SQLException {
        String actionCode;
        User user = new User();
        Account account = new Account();
        Transaction transaction;
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
                    transaction = transactionService.saveTransaction(account, connection, 1);
                    accountService.changeAccountBalance(transaction, connection);
                    break;
                case '4':
                    transaction = transactionService.saveTransaction(account, connection, -1);
                    accountService.changeAccountBalance(transaction, connection);
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

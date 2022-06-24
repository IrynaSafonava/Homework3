package by.it_academy.demo;

import by.it_academy.model.Account;
import by.it_academy.model.Transaction;
import by.it_academy.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


import static by.it_academy.query.AccountQueryExecutor.*;
import static by.it_academy.query.TransactionQueryExecutor.addTransactionToDb;
import static by.it_academy.query.UserQueryExecutor.*;
import static by.it_academy.service.AccountInputReader.*;
import static by.it_academy.service.TransactionInputReader.createTransactionForAccount;
import static by.it_academy.service.UserInputReader.*;

public class Menu {

    public static void executeProgramme(Connection connection) throws SQLException {

        String actionCode;
        do {
            printMenu();
            actionCode = new Scanner(System.in).nextLine();
            switch (actionCode.charAt(0)) {
                case '1':
                    User user = createUser();
                    addUserToDb(user, connection);
                    break;
                case '2':
                    Account newAccount = createAccountForUser();
                    addAccountForUserToDb(newAccount, connection);
                    break;
                case '3':
                    Transaction transaction = createTransactionForAccount();
                    addTransactionToDb(transaction, connection);
                    changeBalanceOfAccount(transaction, connection);
                    break;
                case '4':
                    System.out.println("Thanks for using the programme!");
                    break;
                default:
                    System.out.println("Unknown option. Please enter again");
            }

        } while (!"4".equals(actionCode));
        connection.close();
    }

    public static void printMenu() {
        System.out.println("\nPlease select an action");
        System.out.println("1 - register new user");
        System.out.println("2 - create new account");
        System.out.println("3 - create transaction for account");
        System.out.println("4 - quit\n");
    }
}

package by.it_academy.demo;

import by.it_academy.query.AccountQueryExecutor;
import by.it_academy.query.TransactionQueryExecutor;
import by.it_academy.query.UserQueryExecutor;
import by.it_academy.service.AccountService;
import by.it_academy.service.TransactionService;
import by.it_academy.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Runner {
    private static final String JDBC_DRIVER_PATH = "org.sqlite.JDBC";
    private static final String DATABASE_URL =
            "jdbc:sqlite:c:\\Users\\Iryna\\db\\mytest.db";

    public static void main(String[] args) throws SQLException {

        UserQueryExecutor userQueryExecutor = new UserQueryExecutor();
        UserService userService = new UserService(userQueryExecutor);
        AccountQueryExecutor accountQueryExecutor = new AccountQueryExecutor();
        AccountService accountService = new AccountService(accountQueryExecutor);
        TransactionQueryExecutor transactionQueryExecutor = new TransactionQueryExecutor();
        TransactionService transactionService = new TransactionService(transactionQueryExecutor);

        Menu menu = new Menu(userService, accountService, transactionService );

        if (isDriverExists()) {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            menu.executeProgramme(userService, accountService, transactionService, connection);
        }
    }

    private static boolean isDriverExists() {
        try {
            Class.forName(JDBC_DRIVER_PATH);
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC Driver not found");
            return false;
        }
    }
}


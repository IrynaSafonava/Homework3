package by.it_academy.service;

import by.it_academy.model.Account;
import by.it_academy.query.AccountQueryExecutor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountService {

    private final AccountQueryExecutor accountQueryExecutor;

    public AccountService(AccountQueryExecutor accountQueryExecutor) {
        this.accountQueryExecutor = accountQueryExecutor;
    }

    public void accountInputReader(Account account) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        do {
            try {
                if(account.getUserId() == 0) {
                    System.out.println("Enter user id: ");
                    account.setUserId(Integer.parseInt(scanner.nextLine()));
                }
                System.out.println("Enter account's currency: ");
                account.setCurrency(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        } while (!valid);
    }

    public Account saveAccount(int userId, Connection connection){
    Account account = new Account();
    account.setUserId(userId);
    accountInputReader(account);
        try {
            accountQueryExecutor.addAccountForUserToDb(account, connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return account;
    }
}

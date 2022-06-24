package by.it_academy.service;

import by.it_academy.model.Account;
import java.util.Scanner;

public class AccountInputReader {

    public static Account createAccountForUser() {
        Account account = new Account();
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        do {
            try {
                System.out.println("Enter user's id: ");
                account.setUserId(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter account's currency: ");
                account.setCurrency(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        } while (!valid);
        return account;
    }
}

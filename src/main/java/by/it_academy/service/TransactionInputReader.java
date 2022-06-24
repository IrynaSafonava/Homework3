package by.it_academy.service;

import by.it_academy.model.Transaction;

import java.util.Scanner;

public class TransactionInputReader {

    public static Transaction createTransactionForAccount() {
        Transaction transaction = new Transaction();
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        do {
            try {
                System.out.println("Enter user's account: ");
                transaction.setAccountId(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter amount: ");
                int amount = scanner.nextInt();
                while(amount >= Math.abs(100_000_000)){
                    System.out.println("Transaction cannot exceed 100m");
                    amount = scanner.nextInt();
                }
                transaction.setAmount(Integer.parseInt(scanner.nextLine()));
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
                if (scanner.hasNextInt()) {
                    scanner.nextInt();
                }
            }
        } while (!valid);
        return transaction;
    }
}

package by.it_academy.service;

import by.it_academy.model.Account;
import by.it_academy.model.Transaction;
import by.it_academy.query.TransactionQueryExecutor;

import java.sql.Connection;
import java.util.Scanner;

public class TransactionService {

    private final TransactionQueryExecutor transactionQueryExecutor;
    public TransactionService(TransactionQueryExecutor transactionQueryExecutor) {
        this.transactionQueryExecutor = transactionQueryExecutor;
    }

    public void transactionInputReader(Transaction transaction, int option) {
        Scanner scanner = new Scanner(System.in);
        int requestedAmount = 0;
        boolean valid = true;
        do {
            try {
                if (transaction.getAccountId() == 0) {
                    System.out.println("Enter account id: ");
                    transaction.setAccountId(scanner.nextInt());
                }
                System.out.println("Enter amount: ");
                requestedAmount = scanner.nextInt();
                while (requestedAmount >= 100_000_000) {
                    System.out.println("Transaction cannot exceed 100m");
                    requestedAmount = scanner.nextInt();
                }transaction.setAmount(requestedAmount * option);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (scanner.hasNextInt()) {
                    scanner.nextInt();
                }
            }
        } while (!valid);
    }

    public Transaction saveTransaction(Account account, Connection connection, int option) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getAccountId());
        transactionInputReader(transaction, option);

        try {
            transactionQueryExecutor.addTransactionToDb(transaction, connection, option);
        } catch (Exception e) {
            System.out.println("Problem!");
        }
        return transaction;
    }
}

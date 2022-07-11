package by.it_academy.service;

import by.it_academy.model.Account;
import by.it_academy.model.Transaction;
import by.it_academy.query.TransactionQueryExecutor;
import by.it_academy.demo.InputFormatter;

import java.sql.Connection;
import java.util.Scanner;

public class TransactionService {

    private final TransactionQueryExecutor transactionQueryExecutor;
    private static final int MAX_TRANSACTION_VALUE = 100_000_000;

    public TransactionService(TransactionQueryExecutor transactionQueryExecutor) {
        this.transactionQueryExecutor = transactionQueryExecutor;
    }

    public void transactionInputReader(Transaction transaction, int option) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        do {
            try {
                if (transaction.getAccountId() == 0) {
                    System.out.println("Enter account id: ");
                    transaction.setAccountId((int) scanner.nextDouble());
                }
                System.out.println("Enter amount: ");
                double requestedAmount = InputFormatter.formatInputToDecimal(scanner);
                while (requestedAmount >= MAX_TRANSACTION_VALUE) {
                    System.out.println("Transaction cannot exceed 100m");
                    requestedAmount = InputFormatter.formatInputToDecimal(scanner);
                }
                transaction.setAmount(requestedAmount * option);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (scanner.hasNextDouble()) {
                    scanner.nextDouble();
                }
            }
        } while (!valid);
    }

    public void saveTransaction(Account account, Connection connection, int option) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getAccountId());
        transactionInputReader(transaction, option);

        try {
            transactionQueryExecutor.addTransactionToDb(transaction, connection);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}

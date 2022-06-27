package by.it_academy.service;

import by.it_academy.model.Account;
import by.it_academy.model.Transaction;
import by.it_academy.query.TransactionQueryExecutor;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Scanner;

public class TransactionService {

    private final TransactionQueryExecutor transactionQueryExecutor;

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
                DecimalFormat patternFormat = new DecimalFormat("0.000");
                String result = patternFormat.format(scanner.nextDouble());
                double requestedAmount = Double.parseDouble(result);
                while (requestedAmount >= 100_000_000) {
                    System.out.println("Transaction cannot exceed 100m");
                    result = patternFormat.format(scanner.nextDouble());
                    requestedAmount = Double.parseDouble(result);
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

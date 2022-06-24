package by.it_academy.query;

import by.it_academy.model.Account;
import by.it_academy.model.Transaction;

import java.sql.*;

import static java.lang.String.format;

public class TransactionQueryExecutor {

    public static void addTransactionToDb(Transaction transaction, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format("INSERT INTO Transactions (accountId, amount) VALUES('%d','%d')",
                transaction.getAccountId(), transaction.getAmount()));
        statement.close();
    }
}

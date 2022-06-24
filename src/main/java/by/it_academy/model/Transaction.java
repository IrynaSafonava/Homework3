package by.it_academy.model;

public class Transaction {
    private int transactionId;
    private int accountId;
    private int amount;

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (getTransactionId() != that.getTransactionId()) return false;
        if (getAccountId() != that.getAccountId()) return false;
        return getAmount() == that.getAmount();
    }

    @Override
    public int hashCode() {
        int result = getTransactionId();
        result = 31 * result + getAccountId();
        result = 31 * result + getAmount();
        return result;
    }
}

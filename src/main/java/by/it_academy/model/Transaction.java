package by.it_academy.model;

public class Transaction {
    private int transactionId;
    private int accountId;
    private double amount;

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAmount(double amount) {
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
        return Double.compare(that.getAmount(), getAmount()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getTransactionId();
        result = 31 * result + getAccountId();
        temp = Double.doubleToLongBits(getAmount());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

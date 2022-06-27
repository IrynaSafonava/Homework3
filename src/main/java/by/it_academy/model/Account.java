package by.it_academy.model;

public class Account {
    private int accountId;
    private int userId;
    private double balance;
    private String currency;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getAccountId() != account.getAccountId()) return false;
        if (getUserId() != account.getUserId()) return false;
        if (getBalance() != account.getBalance()) return false;
        return getCurrency() != null ? getCurrency().equals(account.getCurrency()) : account.getCurrency() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getAccountId();
        result = 31 * result + getUserId();
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        return result;
    }
}

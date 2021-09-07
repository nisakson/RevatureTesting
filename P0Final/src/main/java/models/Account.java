package models;

public class Account {
    private double amount;
    private int accountId;

    public Account() {
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amnt) {
        this.amount = amnt;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int id) {
        this.accountId = id;
    }
}

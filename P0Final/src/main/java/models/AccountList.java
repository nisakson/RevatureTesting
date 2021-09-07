package models;

import java.util.HashMap;

public class AccountList {
    private HashMap<Integer, Double> accounts = new HashMap<Integer, Double>();

    public AccountList() {
    }
    
    public void addAccount(int id, double amount) {
    	accounts.put(id, amount);
    }
    
    public HashMap<Integer, Double> getAccounts() {
    	return accounts;
    }
}

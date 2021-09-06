package models;

import java.util.HashMap;

public class CustomerList {
    private HashMap<Integer, String> customers = new HashMap<Integer, String>();

    public CustomerList() {
    }
    
    public void addClient(int id, String name) {
    	customers.put(id, name);
    }
    
    public HashMap<Integer, String> getClients() {
    	return customers;
    }
}

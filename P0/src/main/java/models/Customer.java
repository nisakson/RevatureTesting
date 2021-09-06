package models;

public class Customer {
    private String name;
    private int clientId;

    public Customer() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String nm) {
        this.name = nm;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int id) {
        this.clientId = id;
    }
}

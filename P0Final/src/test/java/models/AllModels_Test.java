package models;

import org.junit.Assert;
import org.junit.Test;

public class AllModels_Test {
    
    @Test
    public void test_clientIdTestTable() {
        //Arrange
    	TestTable test = new TestTable();
    	int id = 1;
    	
        //Act
    	test.setClientId(id);
    	
        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertEquals(id, test.getClientId());
    }
    
    @Test
    public void test_accountIdTestTable() {
        //Arrange
    	TestTable test = new TestTable();
    	int accid = 1;
    	
        //Act
    	test.setAccountId(accid);
    	
        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertEquals(accid, test.getAccountId());
    }
    
    @Test
    public void test_customersCustomerList() {
        //Arrange
    	CustomerList test = new CustomerList();
    	int id = 1;
    	String name = "Bob";
    	
        //Act
    	test.addClient(id, name);
    	
        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertEquals(name, test.getClients().get(id));
        Assert.assertTrue(test.getClients().containsKey(id));
    }
    
    @Test
    public void test_clientIdCustomer() {
        //Arrange
    	Customer test = new Customer();
    	int id = 1;
    	
        //Act
    	test.setClientId(id);
    	
        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertEquals(id, test.getClientId());
    }
    
    @Test
    public void test_nameCustomer() {
        //Arrange
    	Customer test = new Customer();
    	String name = "Bob";
    	
        //Act
    	test.setName(name);
    	
        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertEquals(name, test.getName());
    }
    
    @Test
    public void test_accountsAccountList() {
        //Arrange
    	AccountList test = new AccountList();
    	int accid = 1;
    	double balance = 4.32;
    	
        //Act
    	test.addAccount(accid, balance);
    	
        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(test.getAccounts().containsValue(balance));
        Assert.assertTrue(test.getAccounts().containsKey(accid));
    }
    
    @Test
    public void test_accountIdAccount() {
        //Arrange
    	Account test = new Account();
    	int accid = 1;
    	
        //Act
    	test.setAccountId(accid);
    	
        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertEquals(accid, test.getAccountId());
    }
    
    @Test
    public void test_amountAccount() {
        //Arrange
    	Account test = new Account();
    	double amount = 5.3;
    	
        //Act
    	test.setAmount(amount);
    	
        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(test.getAmount() == amount);
    }
}
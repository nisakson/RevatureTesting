package daos;

import exceptions.NoSQLResultsException;
import models.*;
import utils.ConnectionFactory;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/*
    For every unit test you will follow a similar procedure:
    You will identify the unit under test, and mock anything else.

     Our unit tests will follow the pattern of the 3 A's.
     Arrange - set up the test, identify the SUT, 'mock' and 'stub' any 'collaborators'
     Act - invoke the SUT, perform the actual test
     Assert - check to make sure the expected result holds true
 */


public class TestTableDAO_Test {

    TestTableDAO sut;

    Connection mockConn = ConnectionFactory.getConnection();

    PreparedStatement mockStatement;

    ResultSet mockResults;

    @Before
    public void setUp() {
        sut = new TestTableDAO(mockConn);
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    @Test
    public void test_getSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
        HashMap<Integer, String> customers = new HashMap<Integer, String>();
        customers.put(1, "Jason Smith");
        customers.put(2, "Amanda Smith");
        customers.put(3, "John Cross");
        customers.put(4, "Marty Gras");
        customers.put(5, "Jason A. Lastname");

        //Act
        HashMap<Integer, String> row = sut.get();

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertEquals(customers, row);
    }
    
    @Test
    public void test_getIntIdSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	int id = 1;
    	int acc1 = 900001;
    	double bal1 = 1500.50;
        int acc2 = 900002;
        double bal2 = 2780.25;
        AccountList accounts = new AccountList();
        accounts.addAccount(acc1, bal1);
        accounts.addAccount(acc2, bal2);

        //Act
        AccountList row = sut.get(id);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(row.getAccounts().containsKey(acc1));
        Assert.assertTrue(row.getAccounts().containsKey(acc2));
    }
    
    @Test
    public void test_getBetweenSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	double high = 3000;
        double low = 2500;
        int custid = 1;
        int accid = 900002;

        //Act
        AccountList row = sut.getBetween(custid, low, high);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(row.getAccounts().containsKey(accid));
    }
    
    @Test
    public void test_getAccountSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
        int id = 2;
        int accid = 900003;

        //Act
        AccountList row = sut.getAccount(id, accid);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(row.getAccounts().containsKey(accid));
    }
    
    @Test
    public void test_getNameSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	int id = 1;
        String name = "Nicholas Isakson";

        //Act
        String row = sut.getName(id);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(name.equals(row));
    }
    
    @Test
    public void test_updateCustomerSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	int custid = 1;
    	String name = "Nicholas Isakson";
    	Customer test = new Customer();
    	test.setClientId(custid);
    	test.setName(name);

        //Act
        sut.update(test);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertEquals(test.getName(), sut.getName(custid));
    }
    
    @Test
    public void test_updateAccountSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	int custid = 1;
    	int accid = 900001;
    	double balance = 400.00;
    	Account test = new Account();
    	test.setAccountId(accid);
    	test.setAmount(balance);
    	
    	AccountList tests = new AccountList();
    	tests.addAccount(accid, balance);

        //Act
        sut.update(test);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(sut.getAccount(custid, accid).getAccounts().get(900001)==balance);
    }
    
    @Test
    public void test_addToSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	int custid = 1;
    	int accid = 900001;
    	double change = 50.00;
    	Account test = new Account();
    	test.setAccountId(accid);
    	test.setAmount(change);

        //Act
        sut.addTo(test, accid);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(sut.getAccount(custid, accid).getAccounts().containsValue(400.00));
    }
    
    @Test
    public void test_takeFromSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	int custid = 1;
    	int accid = 900001;
    	double change = 50.00;
    	Account test = new Account();
    	test.setAccountId(accid);
    	test.setAmount(change);

        //Act
        sut.takeFrom(test, accid);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertTrue(sut.getAccount(custid, accid).getAccounts().containsValue(400.00));
    }
    
    @Test
    public void test_saveTestTableSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	TestTable news = new TestTable();
    	news.setClientId(4);
    	news.setAccountId(900007);
    	
    	Account newss = new Account();
    	newss.setAmount(0);
    	newss.setAccountId(900007);

        //Act
        sut.save(news);
        sut.save(newss);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertNotNull(sut.getAccount(4,  900007));
    }
    
    @Test
    public void test_saveAccountSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	AccountList tests = new AccountList();
    	tests.addAccount(900008, 50.00);
    	Account news = new Account();
    	news.setAccountId(900008);
    	news.setAmount(50.00);
    	
    	TestTable test = new TestTable();
    	test.setAccountId(900008);
    	test.setClientId(5);

        //Act
    	sut.save(test);
        sut.save(news);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertNotNull(sut.getAccount(5, 900008));
    }
    
    @Test
    public void test_deleteCustomerSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	int custid = 5;
    	int accid = 900006;
    	Customer news = new Customer();
    	news.setClientId(custid);

        //Act
        sut.delete(news);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertNull(sut.getName(custid));
        Assert.assertNull(sut.getAccount(accid, custid));
    }
    
    @Test
    public void test_deleteAccountSuccess() throws SQLException, NoSQLResultsException {
        //Arrange
    	int custid = 1;
    	Account news = new Account();
    	news.setAccountId(900002);

        //Act
        sut.deleteAcc(news, custid);

        //Assert
        //check equality, assert that the thing is what we want
        Assert.assertNull(sut.getAccount(1, 900002));
    }
}
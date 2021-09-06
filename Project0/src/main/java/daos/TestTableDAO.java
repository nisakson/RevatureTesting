package daos;

import exceptions.NoSQLResultsException;
import models.TestTable;
import utils.ConnectionFactory;
import models.Account;
import models.AccountList;
import models.Customer;
import models.CustomerList;

import org.mariadb.jdbc.internal.util.dao.PrepareResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.RowFilter;

public class TestTableDAO implements Dao<TestTable>{
    //private List<TestTable> testTables;
    Connection connection;

    public TestTableDAO(Connection conn) {
        //testTables = new ArrayList<>();
        connection = conn;
    }
    
    @Override
    public HashMap<Integer, String> get() throws SQLException, NoSQLResultsException {
        String sql = "Select * FROM customers";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
    		CustomerList row = new CustomerList();
    		row.addClient(rs.getInt("customer_id"), rs.getString("name"));
        	while (rs.next()) {
        		row.addClient(rs.getInt("customer_id"), rs.getString("name"));
        	}
        	return row.getClients();
        }
        else {
        	throw new NoSQLResultsException("Customers not found.");
        }
    }

    @Override
    public AccountList get(int id) throws SQLException, NoSQLResultsException {
    	int flag = 0;
        String sql = "Select * FROM accounts_customers WHERE customer_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        
        ResultSet rs = pstmt.executeQuery();

		AccountList row = new AccountList();
        
        while (rs.next()) {
        	flag = 1;
        	String sqltemp = "Select * FROM accounts WHERE account_id = ?";
            PreparedStatement pstmttemp = connection.prepareStatement(sqltemp);
            
            pstmttemp.setInt(1, rs.getInt("account_id"));
            
            ResultSet rstemp = pstmttemp.executeQuery();

            if (rstemp.next()) {
            	row.addAccount(rstemp.getInt("account_id"), rstemp.getDouble("balance"));
            }
        }
        if (flag != 0) {
        	return row;
        }
        else {
        	throw new NoSQLResultsException("ID: " + id + " not found.");
        }
    }

    @Override
    public List<TestTable> getAll() throws SQLException {
        String sql = "SELECT * FROM accounts_customers";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<TestTable> testTableList = new ArrayList<>();

        while(rs.next()) {
            TestTable row = new TestTable();
            row.setClientId(rs.getInt("customer_id"));
            row.setAccountId(rs.getInt("account_id"));
            testTableList.add(row);
        }
        return testTableList;
    }

    @Override
    public void save(TestTable testTable) throws SQLException {
        String sql = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, testTable.getClientId());
        pstmt.setInt(2, testTable.getAccountId());

        if(pstmt.executeUpdate() > 0) {
            pstmt.getResultSet();
        }
    }
    
    @Override
    public void save(Account testTable) throws SQLException {
        String sql = "INSERT INTO accounts (account_id, balance) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, testTable.getAccountId());
        pstmt.setDouble(2, testTable.getAmount());

        if(pstmt.executeUpdate() > 0) {
            pstmt.getResultSet();
        }
    }
    
    @Override
    public void save(Customer testTable) throws SQLException {
        
        String sql = "INSERT INTO customers (customer_id, name) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, testTable.getClientId());
        pstmt.setString(2, testTable.getName());

        if(pstmt.executeUpdate() > 0) {
            pstmt.getResultSet();
        }
    }

    @Override
    public void update(TestTable testTable, String[] params) {

    }

    @Override
    public void delete(TestTable testTable) {

    }

    public void delete(Customer rows) throws SQLException, NoSQLResultsException {
		String sql = "DELETE FROM customers \r\n"
				+ "WHERE customer_id = ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, rows.getClientId());

        if(pstmt.executeUpdate() > 0) {
            pstmt.getResultSet();
        }
        
        TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        AccountList ro = dao.get(rows.getClientId());
        HashMap<Integer, Double> rolist = ro.getAccounts();
        
        for (Map.Entry<Integer, Double> mapElement : rolist.entrySet()) {
        	String sq = "DELETE FROM accounts \r\n"
    				+ "WHERE account_id = ?;";
            PreparedStatement pstm = connection.prepareStatement(sq);
            pstm.setInt(1, mapElement.getKey());

            if(pstm.executeUpdate() > 0) {
                pstm.getResultSet();
            }
        }
        
        String sqls = "DELETE FROM accounts_customers \r\n"
				+ "WHERE customer_id = ?;";
        PreparedStatement pstmts = connection.prepareStatement(sqls);
        pstmts.setInt(1, rows.getClientId());

        if(pstmts.executeUpdate() > 0) {
            pstmts.getResultSet();
        }
	}
    
	public void update(Customer rows) throws SQLException {
		String sql = "UPDATE customers \r\n"
				+ "SET name = ?\r\n"
				+ "WHERE customer_id = ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,rows.getName());
        pstmt.setInt(2, rows.getClientId());

        if(pstmt.executeUpdate() > 0) {
            pstmt.getResultSet();
        }
	}

	public String getName(int id) throws SQLException, NoSQLResultsException {

		String name = "";
        String sql = "Select * FROM customers WHERE customer_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
            
        ResultSet rstemp = pstmt.executeQuery();

        if (rstemp.next()) {
        	name = rstemp.getString("name");
        }
        
        if (name != null) {
        	return name;
        }
        else {
        	throw new NoSQLResultsException("ID: " + id + " not found.");
        }
	}

	public AccountList getBetween(int id, double greater, double less) throws SQLException, NoSQLResultsException {
		int flag = 0;
        String sql = "Select * FROM accounts_customers WHERE customer_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        
        ResultSet rs = pstmt.executeQuery();

		AccountList row = new AccountList();
        
        while (rs.next()) {
        	flag = 1;
        	String sqltemp = "Select * FROM accounts WHERE account_id = ?";
            PreparedStatement pstmttemp = connection.prepareStatement(sqltemp);
            
            pstmttemp.setInt(1, rs.getInt("account_id"));
            
            ResultSet rstemp = pstmttemp.executeQuery();

            if (rstemp.next()) {
            	if (rstemp.getDouble("balance") < less && rstemp.getDouble("balance") > greater) {
            		row.addAccount(rstemp.getInt("account_id"), rstemp.getDouble("balance"));
            	}
            }
        }
        if (flag != 0) {
        	return row;
        }
        else {
        	throw new NoSQLResultsException("ID: " + id + " not found.");
        }
	}

	public AccountList getAccount(int id, int accid) throws SQLException, NoSQLResultsException {
		int flag = 0;        
		String sql = "Select * FROM accounts_customers WHERE customer_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        
        ResultSet rs = pstmt.executeQuery();
        AccountList custAccs = new AccountList();
        
        if (rs.next()) {
        	flag += 1;
        	custAccs.addAccount(rs.getInt("account_id"), 0);
        }
        
        while (rs.next()) {
        	custAccs.addAccount(rs.getInt("account_id"), 0);
        }
        
        HashMap<Integer, Double> custs = custAccs.getAccounts();

		AccountList row = new AccountList();
        
        String sqltemp = "Select * FROM accounts WHERE account_id = ?";
        PreparedStatement pstmttemp = connection.prepareStatement(sqltemp);
            
        pstmttemp.setInt(1, accid);
            
        ResultSet rstemp = pstmttemp.executeQuery();

        if (rstemp.next() && custs.containsKey(rstemp.getInt("account_id"))) {
        	flag += 1;
        	row.addAccount(rstemp.getInt("account_id"), rstemp.getDouble("balance"));
        }
        if (flag == 2) {
        	return row;
        }
        else {
        	throw new NoSQLResultsException("ID: " + id + " not found.");
        }
	}

	public void update(Account rows) throws SQLException {
		String sql = "UPDATE accounts \r\n"
				+ "SET balance = ?\r\n"
				+ "WHERE account_id = ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setDouble(1,rows.getAmount());
        pstmt.setInt(2, rows.getAccountId());

        if(pstmt.executeUpdate() > 0) {
            pstmt.getResultSet();
        }
	}

	public void deleteAcc(Account rows, int id) throws SQLException, NoSQLResultsException {
		PreparedStatement pstm;
		
		String sql = "Select * FROM accounts_customers WHERE customer_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        
        ResultSet rs = pstmt.executeQuery();
        AccountList custAccs = new AccountList();
        
        if (rs.next()) {
        	custAccs.addAccount(rs.getInt("account_id"), 0);
        }
        
        while (rs.next()) {
        	custAccs.addAccount(rs.getInt("account_id"), 0);
        }
        
        HashMap<Integer, Double> custs = custAccs.getAccounts();
		
		if (custs.containsKey(rows.getAccountId())) {
			String sq = "DELETE FROM accounts \r\n"
					+ "WHERE account_id = ?;";
        	pstm = connection.prepareStatement(sq);
        	pstm.setInt(1, rows.getAccountId());
		}
		else {
			throw new NoSQLResultsException("ID: " + id + " not found.");
		}
        if(pstm.executeUpdate() > 0) {
            pstm.getResultSet();
        }
	}

	public void addTo(Account rows, int id) throws SQLException {        
		String sql = "Select * FROM accounts_customers WHERE customer_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        
        ResultSet rs = pstmt.executeQuery();
        AccountList custAccs = new AccountList();
        if (rs.next()) {
        	custAccs.addAccount(rs.getInt("account_id"), 0);
        }
        
        while (rs.next()) {
        	custAccs.addAccount(rs.getInt("account_id"), 0);
        }
        
        HashMap<Integer, Double> custs = custAccs.getAccounts();
        
        String sqltemp = "Select * FROM accounts WHERE account_id = ?";
        PreparedStatement pstmttemp = connection.prepareStatement(sqltemp);
            
        pstmttemp.setInt(1, rows.getAccountId());
            
        ResultSet rstemp = pstmttemp.executeQuery();
        
        if (rstemp.next() && custs.containsKey(rows.getAccountId())) {
    		String sqly = "UPDATE accounts \r\n"
    				+ "SET balance = ?\r\n"
    				+ "WHERE account_id = ?;";
            PreparedStatement pstmty = connection.prepareStatement(sqly);
            double total = Double.sum(rstemp.getDouble("balance"), rows.getAmount());
            pstmty.setDouble(1, total);
            pstmty.setInt(2, rows.getAccountId());
            
            if(pstmty.executeUpdate() > 0) {
                pstmty.getResultSet();
            }
        }
	}

	public void takeFrom(Account rows, int id) throws SQLException {
		String sql = "Select * FROM accounts_customers WHERE customer_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        
        ResultSet rs = pstmt.executeQuery();
        AccountList custAccs = new AccountList();
        if (rs.next()) {
        	custAccs.addAccount(rs.getInt("account_id"), 0);
        }
        
        while (rs.next()) {
        	custAccs.addAccount(rs.getInt("account_id"), 0);
        }
        
        HashMap<Integer, Double> custs = custAccs.getAccounts();
        
        String sqltemp = "Select * FROM accounts WHERE account_id = ?";
        PreparedStatement pstmttemp = connection.prepareStatement(sqltemp);
            
        pstmttemp.setInt(1, rows.getAccountId());
            
        ResultSet rstemp = pstmttemp.executeQuery();
        
        if (rstemp.next() && custs.containsKey(rows.getAccountId())) {
    		String sqly = "UPDATE accounts \r\n"
    				+ "SET balance = ?\r\n"
    				+ "WHERE account_id = ?;";
            PreparedStatement pstmty = connection.prepareStatement(sqly);
            double total;
            if (rstemp.getDouble("balance") >= rows.getAmount())
            	total = Double.sum(rstemp.getDouble("balance"), -rows.getAmount());
            else {
            	total = rstemp.getDouble("balance");
            }
            pstmty.setDouble(1, total);
            pstmty.setInt(2, rows.getAccountId());
            
            if(pstmty.executeUpdate() > 0) {
                pstmty.getResultSet();
            }
        }
		
	}
}

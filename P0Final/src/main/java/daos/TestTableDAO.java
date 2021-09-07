package daos;

import exceptions.NoSQLResultsException;
import models.TestTable;
import utils.ConnectionFactory;
import models.Account;
import models.AccountList;
import models.Customer;
import models.CustomerList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
        	return null;
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
        	return null;
        }
    }

//    @Override
//    public List<TestTable> getAll() throws SQLException {
//        String sql = "SELECT * FROM accounts_customers";
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        ResultSet rs = pstmt.executeQuery();
//
//        List<TestTable> testTableList = new ArrayList<>();
//
//        while(rs.next()) {
//            TestTable row = new TestTable();
//            row.setClientId(rs.getInt("customer_id"));
//            row.setAccountId(rs.getInt("account_id"));
//            testTableList.add(row);
//        }
//        return testTableList;
//    }

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

		String name = null;
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
        return null;
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
        	return null;
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
        	return null;
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
	
	public void reset() throws SQLException {
		String sql = "DROP TABLE IF EXISTS accounts;";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		String sqly = "DROP TABLE IF EXISTS customers;";
		PreparedStatement pstmty = connection.prepareStatement(sqly);
		pstmty.execute();
		
		String sqls = "DROP TABLE IF EXISTS accounts_customers;";
		PreparedStatement pstmts = connection.prepareStatement(sqls);
		pstmts.execute();

		String sql2 = "CREATE TABLE accounts_customers \r\n"
				+ "(\r\n"
				+ "	junction_id INT AUTO_INCREMENT,\r\n"
				+ "	account_id 	INT NOT NULL ,\r\n"
				+ "	customer_id INT NOT NULL,\r\n"
				+ "	INDEX (account_id),\r\n"
				+ "	INDEX (customer_id),\r\n"
				+ "	CONSTRAINT accounts_customers_fk PRIMARY KEY (junction_id)\r\n"
				+ ");";
		PreparedStatement pstmt2 = connection.prepareStatement(sql2);

		pstmt2.executeQuery();
	
		sql = "CREATE TABLE customers\r\n"
				+ "(\r\n"
				+ "    customer_id 	INT NOT NULL,\r\n"
				+ "    name 			VARCHAR(200),\r\n"
				+ "    CONSTRAINT customers_pk PRIMARY KEY (customer_id), \r\n"
				+ "    CONSTRAINT customers_accounts_customers_fk FOREIGN KEY (customer_id) REFERENCES accounts_customers (customer_id)\r\n"
				+ ");";
		pstmt = connection.prepareStatement(sql);
		pstmt.executeQuery();
		
		sql = "CREATE TABLE accounts\r\n"
				+ "(\r\n"
				+ "    account_id 		INT NOT NULL,\r\n"
				+ "    balance 		DECIMAL (10, 2),\r\n"
				+ "    CONSTRAINT accounts_pk PRIMARY KEY (account_id), \r\n"
				+ "    CONSTRAINT accounts_accounts_customers_fk FOREIGN KEY (account_id) REFERENCES accounts_customers (account_id)\r\n"
				+ ");";
		pstmt = connection.prepareStatement(sql);
		pstmt.executeQuery();
		
		sql = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (0001, 900001);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (0001, 900002);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();

		sql = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (0002, 900003);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();

		sql = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (0003, 900004);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();

		sql = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (0004, 900005);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();

		sql = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (0005, 900006);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO customers (customer_id, name) VALUES (0001, \"Jason Smith\");";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO customers (customer_id, name) VALUES (0002, \"Amanda Smith\");";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO customers (customer_id, name) VALUES (0003, \"John Cross\");";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO customers (customer_id, name) VALUES (0004, \"Marty Gras\");";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO customers (customer_id, name) VALUES (0005, \"Jason A. Lastname\");";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO accounts (account_id, balance) VALUES (900001, 1500.50);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO accounts (account_id, balance) VALUES (900002, 2780.25);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO accounts (account_id, balance) VALUES (900003, 150);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO accounts (account_id, balance) VALUES (900004, 13.33);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO accounts (account_id, balance) VALUES (900005, 100000.01);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
		
		sql = "INSERT INTO accounts (account_id, balance) VALUES (900006, 12345.67);";
		pstmt = connection.prepareStatement(sql);
		pstmt.execute();
	}
}

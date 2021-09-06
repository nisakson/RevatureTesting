package controllers;

import daos.TestTableDAO;
import exceptions.NoSQLResultsException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import models.Account;
import models.AccountList;
import models.Customer;
import models.CustomerList;
import models.TestTable;
import utils.ConnectionFactory;

import java.sql.SQLException;
import java.util.HashMap;

public class TestController {
    private static Javalin javalin;

    public static void init(Javalin app) {
        javalin = app;
        
        app.get("/clients", TestController::getClients); // good
        app.post("/clients", TestController::insertClient); // good
        app.get("/clients/:id", TestController::getById); // good
        app.put("/clients/:id", TestController::updateById); // good
        app.delete("/clients/:id", TestController::deleteById); // good
        app.post("/clients/:id/accounts", TestController::createAccount); // good
        app.get("/clients/:id/accounts", TestController::getAccounts); // good
        app.get("/clients/:id/accounts/:id2", TestController::getAccount); // good
        app.put("/clients/:id/accounts/:id2", TestController::updateAccountById); // good
        app.delete("/clients/:id/accounts/:id2", TestController::deleteAccountById); // good
        app.patch("/clients/:id/accounts/:id2", TestController::changeAmount); // 
        app.patch("/clients/:id/accounts/:id2/transfer/:id3", TestController::transferAmount); // 

    }

    public static void testConnection(Context ctx) {
        ctx.status(200);
        ctx.result("Test Received!");
    }

    public static void insertClient(Context ctx) throws SQLException {
        TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String temp = ctx.body();
        TestTable row = new TestTable();
        Customer rows = new Customer();
        Account rowy = new Account();
        
        int id = Integer.parseInt(temp.substring(temp.indexOf("account_id")+13, temp.indexOf(",")));
        int custid = Integer.parseInt(temp.substring(temp.indexOf("customer_id")+14, temp.lastIndexOf(",")));
        String name = temp.substring(temp.indexOf("name")+8, temp.lastIndexOf("\""));
        
        row.setClientId(custid);
        row.setAccountId(id);
        dao.save(row);
        
        rows.setClientId(custid);
        rows.setName(name);
        ctx.status(201);
        dao.save(rows);
        
        rowy.setAccountId(id);
        rowy.setAmount(0);
        dao.save(rowy);
    }

    public static void getById(Context ctx) throws SQLException, NoSQLResultsException {
        TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String rows = dao.getName(Integer.parseInt(ctx.pathParam("id")));
        ctx.json(rows);
    }
    
    public static void getClients(Context ctx) throws SQLException, NoSQLResultsException {
        TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        HashMap<Integer, String> row = dao.get();
        ctx.json(row);
    }
    
    public static void updateById(Context ctx) throws SQLException, NoSQLResultsException {
        TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String temp = ctx.body();
        Customer rows = new Customer();
        
        int custid = Integer.parseInt(ctx.pathParam("id"));
        String name = temp.substring(temp.indexOf("name")+8, temp.lastIndexOf("\""));
        
        rows.setClientId(custid);
        rows.setName(name);
        ctx.status(201);
        dao.update(rows);
    }
    
    public static void deleteById(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        Customer rows = new Customer();
        int custid = Integer.parseInt(ctx.pathParam("id"));
        
        rows.setClientId(custid);
        ctx.status(201);
        dao.delete(rows);
        
    }
    
    public static void createAccount(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String temp = ctx.body();
        TestTable row = new TestTable();
        Account rowy = new Account();
        
        int custid = Integer.parseInt(ctx.pathParam("id"));
        int id = Integer.parseInt(temp.substring(temp.indexOf("account_id")+13, temp.indexOf("account_id")+19));
        
        row.setClientId(custid);
        row.setAccountId(id);
        dao.save(row);
        
        rowy.setAccountId(id);
        rowy.setAmount(0);
        dao.save(rowy);
        
    }
    
    public static void getAccounts(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
    	if (ctx.queryParam("amountGreaterThan") != null) {
    		AccountList row = dao.getBetween(Integer.parseInt(ctx.pathParam("id")), Double.parseDouble(ctx.queryParam("amountGreaterThan")), Double.parseDouble(ctx.queryParam("amountLessThan")));
            ctx.json(row);
    	}
    	else {
    		AccountList row = dao.get(Integer.parseInt(ctx.pathParam("id")));
    		ctx.json(row);
    	}
    }
    
    public static void getAccount(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
    	AccountList row = dao.getAccount(Integer.parseInt(ctx.pathParam("id")), Integer.parseInt(ctx.pathParam("id2")));
    	ctx.json(row);
        
    }
    
    public static void updateAccountById(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String temp = ctx.body();
        Account rows = new Account();
        
        int custid = Integer.parseInt(ctx.pathParam("id"));
        int id = Integer.parseInt(ctx.pathParam("id2"));
        String bal = temp.substring(temp.indexOf("balance")+10);
        bal = bal.replace("}", "");
        Double balance = Double.parseDouble(bal);
        
        AccountList row = dao.getAccount(custid, id);
        
        if (row != null) {
            rows.setAccountId(id);
            rows.setAmount(balance);
            ctx.status(201);
            dao.update(rows);
        }
        else {
        	ctx.status(404);
        }
    }
    
    public static void deleteAccountById(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        Account rows = new Account();
        int custid = Integer.parseInt(ctx.pathParam("id"));
        int id = Integer.parseInt(ctx.pathParam("id2"));      
        
        rows.setAccountId(id);
        ctx.status(201);
        dao.deleteAcc(rows, custid);
        
    }
    
    public static void changeAmount(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String temp = ctx.body();
        Account rows = new Account();
        
        int custid = Integer.parseInt(ctx.pathParam("id"));
        int id = Integer.parseInt(ctx.pathParam("id2"));
        if (temp.contains("deposit")) {
        	String dep = temp.substring(temp.indexOf("deposit")+10);
        	dep = dep.replace("}", "");
        	Double cng = Double.parseDouble(dep);
        	
        	AccountList row = dao.getAccount(custid, id);
            
            if (row != null) {
                rows.setAccountId(id);
                rows.setAmount(cng);
                ctx.status(201);
                dao.addTo(rows, custid);
            }
            else {
            	ctx.status(404);
            }
        }
        if (temp.contains("withdraw")) {
        	String with = temp.substring(temp.indexOf("withdraw")+11);
        	with = with.replace("}", "");
        	Double cng = Double.parseDouble(with);
        	
        	AccountList row = dao.getAccount(custid, id);
            
            if (row != null) {
                rows.setAccountId(id);
                rows.setAmount(cng);
                ctx.status(201);
                dao.takeFrom(rows, custid);
            }
            else {
            	ctx.status(404);
            }
        }
    }
    
    public static void transferAmount(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String temp = ctx.body();
        Account rows = new Account();
        Account ro = new Account();
        
        int custid = Integer.parseInt(ctx.pathParam("id"));
        int i = Integer.parseInt(ctx.pathParam("id2"));
        int id = Integer.parseInt(ctx.pathParam("id3"));
        
        String with = temp.substring(temp.indexOf("amount")+9);
        with = with.replace("}", "");
        Double cng = Double.parseDouble(with);
        
        AccountList row = dao.getAccount(custid, i);
        AccountList r = dao.getAccount(custid, id);
		HashMap<Integer, Double> m = row.getAccounts();
            
        if (row != null) {
        	if (r != null ) {
        		rows.setAccountId(id);
        		rows.setAmount(cng);
        		ro.setAccountId(i);
        		ro.setAmount(cng);
        		ctx.status(201);
        		if (m.get(i) >= cng) {
        			dao.takeFrom(ro, custid);
        			dao.addTo(rows, custid);
        		}
        	}
        }
    }
    
    
}

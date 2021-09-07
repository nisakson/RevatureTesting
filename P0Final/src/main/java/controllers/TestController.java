package controllers;

import daos.TestTableDAO;
import exceptions.NoSQLResultsException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import models.Account;
import models.AccountList;
import models.Customer;
import models.TestTable;
import utils.ConnectionFactory;

import java.sql.SQLException;
import java.util.HashMap;

public class TestController {
    public static Javalin javalin;

    public static void init(Javalin app) {
        javalin = app;
        
        app.get("/clients", TestController::getClients); // complete
        app.post("/clients", TestController::insertClient); // complete
        app.get("/clients/:id", TestController::getById); // complete
        app.put("/clients/:id", TestController::updateById); // complete
        app.delete("/clients/:id", TestController::deleteById); // complete
        app.post("/clients/:id/accounts", TestController::createAccount); // complete
        app.get("/clients/:id/accounts", TestController::getAccounts); // complete
        app.get("/clients/:id/accounts/:id2", TestController::getAccount); // complete
        app.put("/clients/:id/accounts/:id2", TestController::updateAccountById); // complete
        app.delete("/clients/:id/accounts/:id2", TestController::deleteAccountById); // complete
        app.patch("/clients/:id/accounts/:id2", TestController::changeAmount); // complete
        app.patch("/clients/:id/accounts/:id2/transfer/:id3", TestController::transferAmount); // complete
        app.put("/reset", TestController::reset); //

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
        if (rows==null) {
        	ctx.status(404);
        }
        else {
        	ctx.json(rows);
        	ctx.status(200);
        }
    }
    
    public static void getClients(Context ctx) throws SQLException, NoSQLResultsException {
        TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        HashMap<Integer, String> row = dao.get();
        if (row==null) {
        	ctx.status(404);
        }
        else {
        	ctx.json(row);
        	ctx.status(200);
        }
    }
    
    public static void updateById(Context ctx) throws SQLException, NoSQLResultsException {
        TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String temp = ctx.body();
        Customer rows = new Customer();
        
        int custid = Integer.parseInt(ctx.pathParam("id"));
        String name = temp.substring(temp.indexOf("name")+8, temp.lastIndexOf("\""));
        
        rows.setClientId(custid);
        rows.setName(name);
        if (dao.getName(custid)==null) {
        	ctx.status(404);
        }
        else {
        	dao.update(rows);
        	ctx.status(201);
        }
    }
    
    public static void deleteById(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        Customer rows = new Customer();
        int custid = Integer.parseInt(ctx.pathParam("id"));
        
        rows.setClientId(custid);
        if (dao.getName(custid)==null) {
        	ctx.status(404);
        }
        else {
        	dao.delete(rows);
        	ctx.status(205);
        }
    }
    
    public static void createAccount(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        String temp = ctx.body();
        TestTable row = new TestTable();
        Account rowy = new Account();
        
        int custid = Integer.parseInt(ctx.pathParam("id"));
        int id = Integer.parseInt(temp.substring(temp.indexOf("account_id")+13, temp.indexOf("account_id")+19));
        
        if (dao.getName(custid)==null) {
        	ctx.status(404);
        }
        else {
        	row.setClientId(custid);
        	row.setAccountId(id);
        	dao.save(row);
        
        	rowy.setAccountId(id);
        	rowy.setAmount(0);
        	dao.save(rowy);
        	ctx.status(201);
        }
    }
    
    public static void getAccounts(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
    	int custid;
    	if (ctx.queryParam("amountGreaterThan") != null) {
    		custid = Integer.parseInt(ctx.pathParam("id"));
    		AccountList row = dao.getBetween(Integer.parseInt(ctx.pathParam("id")), Double.parseDouble(ctx.queryParam("amountGreaterThan")), Double.parseDouble(ctx.queryParam("amountLessThan")));
    		if (dao.getName(custid)==null) {
            	ctx.status(404);
            }
            else {
        		ctx.json(row);
            	ctx.status(201);
            }
    	}
    	else {
    		custid = Integer.parseInt(ctx.pathParam("id"));
    		AccountList row = dao.get(Integer.parseInt(ctx.pathParam("id")));
    		if (dao.getName(custid)==null) {
            	ctx.status(404);
            }
            else {
        		ctx.json(row);
            	ctx.status(201);
            }
    	}
    }
    
    public static void getAccount(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
    	AccountList row = dao.getAccount(Integer.parseInt(ctx.pathParam("id")), Integer.parseInt(ctx.pathParam("id2")));
    	int custid = Integer.parseInt(ctx.pathParam("id"));
    	int id = Integer.parseInt(ctx.pathParam("id2"));
    	
    	if (dao.getName(custid)==null) {
        	ctx.status(404);
        }
    	if (dao.getAccount(custid, id)==null) {
    		ctx.status(404);
    	}
        else {
    		ctx.json(row);
        	ctx.status(201);
        }
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
        
        if (dao.getName(custid)==null) {
        	ctx.status(404);
        }
    	if (dao.getAccount(custid, id)==null) {
    		ctx.status(404);
    	}
        else {
        	rows.setAccountId(id);
            rows.setAmount(balance);
            dao.update(rows);
            ctx.status(201);
        }
    }
    
    public static void deleteAccountById(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        Account rows = new Account();
        int custid = Integer.parseInt(ctx.pathParam("id"));
        int id = Integer.parseInt(ctx.pathParam("id2"));      
        
        rows.setAccountId(id);
        
        if (dao.getName(custid)==null) {
        	ctx.status(404);
        }
    	if (dao.getAccount(custid, id)==null) {
    		ctx.status(404);
    	}
        else {
            dao.deleteAcc(rows, custid);
            ctx.status(205);
        }
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
        	
        	if (dao.getName(custid)==null) {
            	ctx.status(404);
            }
        	if (dao.getAccount(custid, id)==null) {
        		ctx.status(404);
        	}
            else {
                rows.setAccountId(id);
                rows.setAmount(cng);
                dao.addTo(rows, custid);
                ctx.status(201);
            }
        }
        if (temp.contains("withdraw")) {
        	String with = temp.substring(temp.indexOf("withdraw")+11);
        	with = with.replace("}", "");
        	Double cng = Double.parseDouble(with);
        	
        	if (dao.getName(custid)==null) {
            	ctx.status(404);
            }
        	if (dao.getAccount(custid, id)==null) {
        		ctx.status(404);
        	}
        	else if (dao.getAccount(custid, id)==null || dao.getAccount(custid, id).getAccounts().get(id) < cng) {
        		ctx.status(422);
        	}
            else {
                rows.setAccountId(id);
                rows.setAmount(cng);
                dao.takeFrom(rows, custid);
                ctx.status(201);
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

		HashMap<Integer, Double> m = new HashMap<Integer, Double>();

        if (dao.getName(custid)==null) {
        	ctx.status(404);
        }
    	if (dao.getAccount(custid, id)==null) {
    		ctx.status(404);
    	}
    	else  {
        	m = row.getAccounts();
    		if (m.get(i) < cng) {
    			ctx.status(422);
    		}
    		else {
            	rows.setAccountId(id);
        		rows.setAmount(cng);
        		ro.setAccountId(i);
        		ro.setAmount(cng);
        		ctx.status(201);
        		dao.takeFrom(ro, custid);
        		dao.addTo(rows, custid);
            }
    	}
    }
    
    public static void reset(Context ctx) throws SQLException, NoSQLResultsException {
    	TestTableDAO dao = new TestTableDAO(ConnectionFactory.getConnection());
        
        dao.reset();
        ctx.status(205);
    }
}

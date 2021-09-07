package daos;

import exceptions.NoSQLResultsException;
import models.Account;
import models.AccountList;
import models.Customer;

import java.sql.SQLException;
import java.util.HashMap;

public interface Dao<T> {

	HashMap<Integer, String> get() throws SQLException, NoSQLResultsException;
	
    AccountList get(int id) throws SQLException, NoSQLResultsException;

    void save(T t) throws SQLException;

	void save(Customer t) throws SQLException;

	void save(Account t) throws SQLException;
}
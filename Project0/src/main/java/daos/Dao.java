package daos;

import exceptions.NoSQLResultsException;
import models.Account;
import models.AccountList;
import models.Customer;
import models.CustomerList;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Dao<T> {

	HashMap<Integer, String> get() throws SQLException, NoSQLResultsException;
	
    AccountList get(int id) throws SQLException, NoSQLResultsException;

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    void update(T t, String[] params);

    void delete(T t);

	void save(Customer t) throws SQLException;

	void save(Account t) throws SQLException;
}
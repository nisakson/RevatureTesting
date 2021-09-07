package exceptions;

public class NoSQLResultsException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSQLResultsException(String message) {
        super(message);
    }
}

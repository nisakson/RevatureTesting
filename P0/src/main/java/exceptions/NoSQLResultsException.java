package exceptions;

import io.javalin.http.Context;

public class NoSQLResultsException extends Exception{
    public NoSQLResultsException(String message) {
        super(message);
    }
}

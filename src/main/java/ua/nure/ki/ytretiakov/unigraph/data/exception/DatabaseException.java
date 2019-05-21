package ua.nure.ki.ytretiakov.unigraph.data.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(final Exception e) {
        super(e);
    }

    public DatabaseException(final String message){
        super(message);
    }
}

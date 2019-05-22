package ua.nure.ki.ytretiakov.unigraph.data.exception;

import org.apache.log4j.Logger;

public class DatabaseException extends RuntimeException {

    private static Logger logger = Logger.getLogger(DatabaseException.class);

    public DatabaseException(final Exception e) {
        super(e);
        logger.info(e.getMessage());
    }

    public DatabaseException(final String message){
        super(message);
        logger.info(message);
    }
}

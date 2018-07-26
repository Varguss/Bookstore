package model;

public class DAOException extends RuntimeException {
    DAOException() {

    }

    DAOException(String message) {
        super(message);
    }

    DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    DAOException(Throwable cause) {
        super(cause);
    }

    DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

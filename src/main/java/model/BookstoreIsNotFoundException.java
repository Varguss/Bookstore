package model;

public class BookstoreIsNotFoundException extends Exception {
    BookstoreIsNotFoundException() {

    }

    BookstoreIsNotFoundException(String message) {
        super(message);
    }

    BookstoreIsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    BookstoreIsNotFoundException(Throwable cause) {
        super(cause);
    }

    BookstoreIsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

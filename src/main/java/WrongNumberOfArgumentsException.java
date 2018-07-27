class WrongNumberOfArgumentsException extends Exception {
    WrongNumberOfArgumentsException() {

    }

    WrongNumberOfArgumentsException(String message) {
        super(message);
    }

    WrongNumberOfArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    WrongNumberOfArgumentsException(Throwable cause) {
        super(cause);
    }

    WrongNumberOfArgumentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package lt.staupasedvinas.utils;

import lt.staupasedvinas.utils.BadInputException;

public class BadDataException extends Exception {
    private String message;
    public BadDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package model.exceptions;

public class NonExistentIdException extends Exception {
    public NonExistentIdException(String message) {
        super(message);
    }
}

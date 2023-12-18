package gui.view.util.exception;

/**
 * This exception should be thrown when a player submit an invalid input
 */

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}

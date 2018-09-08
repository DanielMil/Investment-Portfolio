package assignment3;

/**
 * Custom exception thrown when numerical input is invalid. 
 * @author danielmil
 */
public class NumberOutOfBoundsException extends Exception {
    
    public NumberOutOfBoundsException (String message) {
        super(message);
    }
    
    public NumberOutOfBoundsException () {
        super("Invalid number entered.");
    }
}

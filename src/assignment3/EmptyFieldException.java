package assignment3;

/**
 * Custom exception thrown when an input field is empty. 
 * @author danielmil
 */
public class EmptyFieldException extends Exception {
    
    public EmptyFieldException (String message) {
        super(message);
    }
    
    public EmptyFieldException () {
        super("Empty field entered.");
    }
}

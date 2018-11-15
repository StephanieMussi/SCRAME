/**
 * Exception class for incorrect input (String) values
 */
public class isInvalidInputException extends Exception {

    public isInvalidInputException() {
        super("Input is invalid!!");
    }

    public isInvalidInputException(String Object) {
        super("Input is invalid. Please enter " + Object);
    }
}

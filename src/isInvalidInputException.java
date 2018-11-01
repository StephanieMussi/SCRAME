/***
 * Exception class for incorrect input values
 */
public class isInvalidInputException extends Exception {

    public isInvalidInputException() {
        super("Input is invalid!!");
    }

    public isInvalidInputException(String object) {
        super("Input is invalid. Please enter " + object);
    }
}

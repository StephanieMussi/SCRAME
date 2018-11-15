/**
 * Exception class to deal with invalid registration - Not found
 */
public class isRegisterationException extends Exception {
    public isRegisterationException(String object) {

        super(object + " not found!");
    }

}

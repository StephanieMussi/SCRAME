/**
 * Exception class dealing with insufficient input values
 */
public class isNotSufficientException extends Exception {

    public isNotSufficientException(){
        super("Input is not sufficient enough to parse.");
    }

    public isNotSufficientException(String object){
        super("Number of " + object + " is not sufficient.");
    }

}

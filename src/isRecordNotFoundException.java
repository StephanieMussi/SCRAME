/***
 * Exception class when dealing record not found
 */
public class isRecordNotFoundException extends Exception {

    public isRecordNotFoundException(String Object){
        super(Object + " is not found in Records.");
    }
}

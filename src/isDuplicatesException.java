/***
 * Exception class avoid duplicate records
 */
public class isDuplicatesException extends Exception{

    public isDuplicatesException(String object){ super(object + " already exist in database!"); }

}

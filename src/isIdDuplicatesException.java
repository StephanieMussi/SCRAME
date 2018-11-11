public class isIdDuplicatesException extends Exception{

    public isIdDuplicatesException(int Object){
        super(Object + " already exist in database!");
    }

}

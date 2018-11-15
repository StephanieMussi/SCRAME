/**
 * Exception class when dealing max capacity for Courses
 */
public class isFullException extends Exception{

    public isFullException(){
        super("Course is at max capacity!");
    }

}


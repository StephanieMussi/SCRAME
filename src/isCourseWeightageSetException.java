/***
 * Exception class when dealing weightage re-entering
 */
public class isCourseWeightageSetException extends Exception {

    public isCourseWeightageSetException() {
        super("Course components weightage has already been set.");
    }
}

import java.util.HashMap;

/***
 * Record class
 * Stores and retrieves marks of Course
 */
public class CourseMarkRecords {
    /*
     * Store the marks record of each assessment in Course
     * Using Hashmap?
     */
    private HashMap<Assessment, Double> componentMarks;
    /*
     * Boolean check to confirm if work is graded
     */
    private boolean graded = false;

    /***
     * CourseMarkRecords - Constructor
     */
    public CourseMarkRecords() {
        this.componentMarks = new HashMap<Assessment, Double>();
    }

    /*
     * Get Component mark of a student
     */
    public Double getComponentMarks(Assessment component) {
        componentMarks.putIfAbsent( component, 0.0 );
        return componentMarks.get( component );
    }

    /*
     * Set Component mark of a student
     */
    public void setComponentMarks(Assessment component, double mark) {
        if (mark <= 0) {
            mark = 0;
        } else if (mark >= 100) {
            mark = 100;
        }
        componentMarks.put( component, mark );
    }

    /*
     * Grading verifier
     */
    public boolean isGraded() {
        return graded;
    }
}

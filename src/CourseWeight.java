import java.io.Serializable;
import java.util.ArrayList;

/***
 * Weightage class
 * Handles and manages Course Components and Weightages
 * Tagged to student enrolled to Course
 */
public class CourseWeight implements Serializable {

    /*
     * Course's Examination
     */
    private Assessment examination;
    /*
     * List of Coursework for the Course
     */
    private ArrayList<Assessment> courseWork;


    /***
     * Constructor for CourseWeight
     */
    public CourseWeight(Assessment exam, ArrayList<Assessment> courseWork) {
        this.examination = exam;
        this.courseWork = courseWork;
    }

    /***
     * Getters for Instances
     */

    /*
     * Get examination of Course
     */
    public Assessment getExamination() {
        return examination;
    }

    /*
     * Get list of Coursework (sub components) of Course
     */
    public ArrayList<Assessment> getCourseWork() {
        return courseWork;
    }
}

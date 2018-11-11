import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/***
 * Weightage class
 * Handles and manages Course Components and Weightages
 * Tagged to student enrolled to Course
 */
public class CourseWeight implements Serializable {

    /*
     * Call Assessment class
     */
    static Assessment assessment;
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

    /*
     * Get particular student's mark records of his/her registered Course
     */



    /***
     * Setters for Instances
     */

    /*
     * Set Examination weightage for Course
     */
    public void setExamination(Assessment examination) {
        this.examination = examination;
    }

    /*
     * Set Coursework weightage for Course
     */
    public void setCourseWork(ArrayList<Assessment> courseWork) {
        this.courseWork = courseWork;
    }


    /***
     * Add methods
     */

    /*
     * Add Coursework into Course
     * (Sub-components of Coursework)
     * i.e Assignment, Class participation, Lab assessments
     */
    public void addCoursework(Assessment coursework) {
        if (!courseWork.contains( coursework )) {
            courseWork.add( coursework );
        }
    }
}

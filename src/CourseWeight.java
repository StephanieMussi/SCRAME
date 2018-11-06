import java.util.ArrayList;
import java.util.HashMap;

/***
 * Weightage class
 * Handles and manages Course Components and Weightages
 * Tagged to student enrolled to Course
 */
public class CourseWeight {

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
    /*
     * HashMap
     * Tagged each student to his/her marks of Course
     */
    private HashMap<StudentInfo, CourseMarkRecords> studentMarkRecords;
    /*
     * Number of Course work in Course
     */
    private int numberOfCourseWork;

    /***
     * Constructor for CourseWeight
     */
    public CourseWeight() {
        this.examination = null;
        this.courseWork = new ArrayList<Assessment>();
        this.studentMarkRecords = new HashMap<StudentInfo, CourseMarkRecords>();
        this.numberOfCourseWork = numberOfCourseWork;
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
    public CourseMarkRecords getStudentMarkRecords(StudentInfo studentname) {
        studentMarkRecords.putIfAbsent( studentname, new CourseMarkRecords() );
        return studentMarkRecords.get( studentname );

    }

    /*
     * Get number of Coursework of Course
     */
    public int getNumberOfCourseWork() {
        return numberOfCourseWork;
    }

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

    /*
     * Set coursework marks into student's record
     */
    public void setStudentMarkRecords(StudentInfo student, Assessment components, double mark) {
        CourseMarkRecords records = getStudentMarkRecords( student );
        records.setComponentMarks( components, mark );
    }

    /*
     * Set number of coursework in Course
     */
    public void setNumberOfCourseWork(int numberOfCourseWork) {

        this.numberOfCourseWork = numberOfCourseWork;
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

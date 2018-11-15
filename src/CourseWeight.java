import java.io.Serializable;
import java.util.ArrayList;

/**
 * Weightage class
 * Handles and manages Course Components and Weightages
 * Tagged to student enrolled to Course
 */
public class CourseWeight implements Serializable {

    private Assessment examination;

    private ArrayList<Assessment> courseWork;


    /**
     * Constructor for CourseWeight
     */
    public CourseWeight(Assessment exam, ArrayList<Assessment> courseWork) {
        this.examination = exam;
        this.courseWork = courseWork;
    }

    /**
     * getters for Instances
     * @return
     */
    public Assessment getExamination() {
        return examination;
    }

    /**
     * getter
     * @return ArrayList<Assessment>
     */
    public ArrayList<Assessment> getCourseWork() {
        return courseWork;
    }
}

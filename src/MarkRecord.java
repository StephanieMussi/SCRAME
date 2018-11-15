import java.io.Serializable;
import java.util.ArrayList;

/***
 * Record class
 * Stores and retrieves marks of Course
 */
public class MarkRecord implements Serializable {

    private Registration registration;
    private Course course;
    //private boolean graded = false;
    private double[] marksCA;
    private double markExam;

    MarkRecord(Registration registration, Course course) {
        this.registration = registration;
        this.course = course;
        initialize();
    }

    private void initialize() {
        markExam = 0;
        ArrayList<Assessment> courseWorkList = course.getCourseWeightage().getCourseWork();
        if (courseWorkList != null)
            marksCA = new double[courseWorkList.size()];
    }

    // getters and setters
    public Registration getRegistration() {
        return registration;
    }


    public double[] getMarksCA() {
        return marksCA;
    }

    public void setMarksCA(double mark, int index) {
        marksCA[index] = mark;
    }

    public double getMarkExam() {
        return markExam;
    }

    public void setMarkExam(double markExam) {
        this.markExam = markExam;
    }
}

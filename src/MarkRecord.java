import java.io.Serializable;
import java.util.ArrayList;

/**
 * Record class
 * Stores and retrieves marks of Course
 */
public class MarkRecord implements Serializable {

    private Registration registration;
    private Course course;
    //private boolean graded = false;
    private double[] marksCA;
    private double markExam;

    /**
     * constructor
     * @param registration
     * @param course
     */
    MarkRecord(Registration registration, Course course) {
        this.registration = registration;
        this.course = course;
        initialize();
    }

    /**
     * initialize mark record structure, including
     * exam and ca arrays
     */
    private void initialize() {
        markExam = 0;
        ArrayList<Assessment> courseWorkList = course.getCourseWeightage().getCourseWork();
        if (courseWorkList != null)
            marksCA = new double[courseWorkList.size()];
    }

    /**
     * @return Registration
     */
    public Registration getRegistration() {
        return registration;
    }

    /**
     * @return double
     */
    public double getMarkExam() {
        return markExam;
    }

    /**
     * @return double[]
     */
    public double[] getMarksCA() {
        return marksCA;
    }


    /**
     * @param mark
     * @param index
     */
    public void setMarksCA(double mark, int index) {
        marksCA[index] = mark;
    }

    /**
     * @param markExam
     */
    public void setMarkExam(double markExam) {
        this.markExam = markExam;
    }
}

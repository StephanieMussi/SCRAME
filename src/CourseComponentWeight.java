import java.util.ArrayList;
import java.util.HashMap;

/***
 * Weightage class
 * Handles and manages Course Component and Weightages tagged to Student to a Course
 */
public class CourseComponentWeight {

    static Course course;
    static Assessment assess;

    /*
     * Course's Examination
     */
    private Assessment examination;
    /*
     * List of Coursework for the Course
     */
    private ArrayList<Assessment> courseWork;

    private HashMap<StudentInfo, GradingRecords> studentMarkRecords;
    /*
     * Number of Course work in Course
     */
    private int numberOfCourseWork;

    public CourseComponentWeight() {
        this.examination = null;
        this.courseWork = new ArrayList<Assessment>();
        this.studentMarkRecords = new HashMap<StudentInfo, GradingRecords>();

    }

    /*
     * Get Examination of Course
     */
    public Assessment getExamination() {
        return examination;
    }

    /*
     * Get list of sub-components (Coursework) for a Course
     */
    public ArrayList<Assessment> getCourseWork() {
        return courseWork;
    }

    /*
     * Get list of Course components tagged to student
     */
    public GradingRecords getStudentRecords(StudentInfo Student) {
        studentMarkRecords.putIfAbsent( Student, new GradingRecords() );
        return studentMarkRecords.get( Student );
    }

    /*
     * Set Examination weightage for the Course
     */
    public void setExamination(Assessment examination) {
        this.examination = examination;
    }

    /*
     * Set mark of coursework into Student's record
     */
    public void setStudentComponentMark(StudentInfo student, Assessment components, double mark){
        GradingRecords Record = getStudentRecords( student );
        Record.setComponentMarks(components, mark);
    }
    /*
     * Add Coursework into a Course
     * (Sub-components of Coursework)
     */
    public void addCourseWork(Assessment subcomponent) {
        if (!courseWork.contains( subcomponent )) {
            courseWork.add( subcomponent );
        }
    }


    /*
     * Get number of Coursework of Course
     */
    public int getNumberOfCourseWork() {

        return numberOfCourseWork;
    }

    /*
     * Set total weightage of Course
     * Based on Exam weightage and Coursework weightage

    public void setWeightage(double examWeight, double[] courseWorkWeight) {
        this.examWeight = examWeight;
        this.courseWorkWeight = courseWorkWeight;
        this.numberOfCourseWork = courseWorkWeight.length;

    }
    */
}


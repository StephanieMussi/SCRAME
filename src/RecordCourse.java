/***
 * Record class
 * Stores and retrieves marks of Course
 */
public class RecordCourse {

    private String courseName;
    private String studentName;
    private int lectureType;
    private int tutorialType;
    private int labType;

    private double examWeight;
    private double[] courseworkWeightage;
    private double overallCourseMarks;
    private String courseGrade;

    private boolean graded = false;

    /***
     * @param courseName
     * @param studentName
     * @param lectureType
     * @param tutorialType
     * @param labType
     */
    public RecordCourse(String courseName, String studentName, int lectureType, int tutorialType, int labType) {
        this.courseName = courseName;
        this.studentName = studentName;
        this.lectureType = lectureType;
        this.tutorialType = tutorialType;
        this.labType = labType;
    }

    /***
     * Getters
     */
    public String getCourseName() {
        return courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getLectureType() {
        return lectureType;
    }

    public int getTutorialType() {
        return tutorialType;
    }

    public int getLabType() {
        return labType;
    }

    public double getExamWeight() {
        return examWeight;
    }

    public double[] getCourseworkWeightage() {
        return courseworkWeightage;
    }

    public int numbOfCourseworkType() {
        return courseworkWeightage.length;
    }

    public double getOverallCourseMarks() {
        return overallCourseMarks;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    /***
     * @param examWeight
     */
    public void setExamWeight(double examWeight) {
        this.examWeight = examWeight;
    }

    /***
     * @param courseworkWeightage
     */
    public void setCourseworkWeightage(double[] courseworkWeightage) {
        if (courseworkWeightage.length == this.courseworkWeightage.length) {
            for (int i = 0; i < courseworkWeightage.length; i++) {
                this.courseworkWeightage[i] = courseworkWeightage[i];
            }
        }
    }

    public boolean isGraded() {
        return graded;
    }
}

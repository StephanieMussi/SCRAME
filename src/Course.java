import java.io.Serializable;
import java.util.ArrayList;

/***
 * Course class (Model)
 * Stores and Retrieves Course Information - Adding Lectures, Tutorials and Lab into a Course
 */
public class Course implements Serializable {
    /*
     * Course code: CZ2002 --> 2002
     */
    private int courseCode;
    /*
     * Course name: Objected Oriented Design Programming
     */
    private String Cname;
    /*
     * Professor ID in charged of Course
     */
    private int profId;
    /*
     * Total capacity of created Course
     */
    private int capacityOfCourse;
    /*
     * Exam weightage of Course
     */
    private double examWeight;
    /*
     * Coursework weightage of Course
     */
    private double[] courseWorkWeight;
    /*
     * Number of Coursework in the course
     */
    private int numberOfCourseWork;
    // private List<IndexCourse> indexList;     // new ArrayList<indexList>(  );
    /*
     * Lecture session of a course; there can only be one lecture per course
     */
    private CourseIndex Lecture;
    /*
     * Tutorial session of a course; number of tutorial is based on Indexes of Course
     * i.e Index Tutorial group SS1(Index 1101), SS2(Index 1102)
     */
    private ArrayList<CourseIndex> tutorialGrps;
    /*
     * Lab session of a course; number of lab is based on Indexes of Course
     * i.e Index lab group SS1(Index 1101), SS2(Index 1102)
     */
    private ArrayList<CourseIndex> laboratoryGrps;

    /***
     * @param courseCode
     * @param Cname
     * @param profId
     */
    public Course(int courseCode, String Cname, int profId) {
        this.courseCode = courseCode;
        this.Cname = Cname;
        this.profId = profId;
        Lecture = null;
        tutorialGrps = new ArrayList<CourseIndex>();
        laboratoryGrps = new ArrayList<CourseIndex>();
    }

    /*
     * Get Course code
     */
    public int getCourseCode() {

        return courseCode;
    }

    /*
     * Get Course name
     */
    public String getCourseName() {

        return Cname;
    }

    /*
     * Get total capacity of Course
     */
    public int getCapacityOfCourse() {
        return capacityOfCourse;
    }

    /*
     * Get Professor ID of Course
     */
    public int getProfId() {

        return profId;
    }

    /*
     * Get Exam weightage of Course
     */
    public double getExamWeight() {

        return examWeight;
    }

    /*
     * Get Coursework weightage of Course
     */
    public double[] getCourseWorkWeight() {

        return courseWorkWeight;
    }

    /*
     * Get number of Coursework of Course
     */
    public int getNumberOfCourseWork() {

        return numberOfCourseWork;
    }

    /*
     * Get lecture from Course
     */
    public CourseIndex getLecture() {
        return Lecture;
    }

    /*
     * Get tutorial groups from a Course
     */
    public ArrayList<CourseIndex> getTutorialGrps() {
        return tutorialGrps;
    }

    /*
     * Get lab groups from a Course
     */
    public ArrayList<CourseIndex> getLaboratoryGrps() {

        return laboratoryGrps;
    }

    /*
     * Set total weightage of Course
     * Based on Exam weightage and Coursework weightage
     */
    public void setWeightage(double examWeight, double[] courseWorkWeight) {
        this.examWeight = examWeight;
        this.courseWorkWeight = courseWorkWeight;
        this.numberOfCourseWork = courseWorkWeight.length;

    }

    /*
     * Add a Lecture into a Course
     */
    public void addLectures(CourseIndex lecture) {
        this.Lecture = lecture;
    }

    /*
     * Add tutorial groups (Indexes) into a Course
     */
    public void addTutorials(int[] capacity) {
        int i = 0;
        /*
         * Traverse through capacity array of Student's assigned tutorial index
         * Proceeds to add into the tutorial with the tagged index of Course
         */
        while (i < capacity.length) {
            Tutorial newTut = new Tutorial( i, capacity[i] );
            i++;
        }
    }

    /*
     * Add lab  groups (Indexes) into a Course
     */
    public void addLab(int[] capacity) {
        int i = 0;
        /*
         * Traverse through capacity array of Student's assigned Lab index
         * Proceeds to add into the Lab with the tagged index of Course
         */
        while (i < capacity.length) {
            Laboratory newLab = new Laboratory( i, capacity[i] );
            i++;
        }
    }

    /*
     * Set Lecture to Course
     */
    public void setLecture(CourseIndex Lecture) {
        this.Lecture = Lecture;
    }

    /*
     * Set tutorial group to an INDEX of Course
     */
    public void setTutGroup(ArrayList<CourseIndex> tutorialGrps) {
        this.tutorialGrps = tutorialGrps;
    }

    /*
     * Set Tutorial vacancy
     */
    public void setTutVacancy(int index) {

        tutorialGrps.get( index ).setVacancy();
    }

    /*
     * Set lab group to an INDEX of Course
     */
    public void setLabGroup(ArrayList<CourseIndex> laboratoryGrps) {
        this.laboratoryGrps = laboratoryGrps;
    }

    /*
     * Set Lab vacancy
     */
    public void setlabVacancy(int index) {

        laboratoryGrps.get( index ).setVacancy();
    }

    /*
     * Course verifiers
     */
    public boolean isCourseValidatable() {
        if (examWeight != 0 && courseWorkWeight != null && numberOfCourseWork >= 0) {
            return true;
        } else {
            return false;
        }
    }


}

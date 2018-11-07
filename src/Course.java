import java.io.Serializable;
import java.util.ArrayList;

/***
 * Course class (Model)
 * Stores and Retrieves Course Information - Adding Lectures, Tutorials and Lab into a Course
 * Manages Course assessments and enrolled students
 */
public class Course implements Serializable {

    /*
     * Call CourseComponentWeight class
     */
    static CourseWeight weightage;
    /*
     * Professor ID in charged of Course
     */
    private int professorId;
    /*
     * Course code: CZ2002 -> 2002
     */
    private int courseCode;
    /*
     * Course name: Objected Oriented Design Programming
     */
    private String courseName;
    /*
     * Course Credits: AUs
     */
    private int courseAU;
    /*
     * Total capacity of created Course
     */
    private int maxCapacity;
    /*
     * Lecture session of Course
     * There can only be one lecture per course
     */
    private CourseIndex lecture;
    /*
     * Tutorial session of a course; number of tutorial is based on Indexes of Course
     * i.e Index Tutorial group SS1(Index 1101), SS2(Index 1102)
     */
    private ArrayList<CourseIndex> tutorialIndexes;
    /*
     * Lab session of a course; number of lab is based on Indexes of Course
     * i.e Index lab group SS1(Index 1101), SS2(Index 1102)
     */
    private ArrayList<CourseIndex> laboratoryIndexes;
    /*
     * Registered students of Course
     */
    private ArrayList<StudentInfo> registeredStudents;
    /*
     * Weightages of Course's Components
     */
    private CourseWeight courseWeightage;

    /***
     * Constructor for Course
     * @param professorId
     * @param courseCode
     * @param courseName
     */
    public Course(int professorId, int courseCode, String courseName, int courseAU) {
        this.professorId = professorId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseAU = courseAU;
        this.lecture = null;
    }

    /***
     * Adders
     */

    /*
     * Adding tutorial indexes into Course
     * capacity parameter stores total number of students in each group
     */
    public void addTutorialIndex(int[] capacity){
        int i = 0;
        for(i = 0; i < capacity.length; i++){
            CourseIndex tutorialIndex = new CourseIndex( i, capacity[i] );
            tutorialIndexes.add( tutorialIndex );
        }
    }

    /*
     * Adding lecture indexes into Course
     * capacity parameter stores total number of students
     */
    public void addLabIndex(int[] capacity){
        int i = 0;
        for(i = 0; i < capacity.length; i++){
            CourseIndex labIndex = new CourseIndex( i, capacity[i] );
            laboratoryIndexes.add( labIndex );
        }
    }

    /**
     * Get methods for Instances
     */

    /*
     * get Professor ID in charged of Course
     */
    public int getProfessorId() {
        return professorId;
    }

    /*
     * Get Course code; CZ2002 -> 2002
     */
    public int getCourseCode() {
        return courseCode;
    }

    /*
     * Get Course Name; Object Oriented and Design Programming
     */
    public String getCourseName() {
        return courseName;
    }

    /*
     * Get Max capacity of Course
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /*
     * Get Course AU
     */
    public int getCourseAU() {
        return courseAU;
    }

    /*
     * Get lecture from Course
     */
    public CourseIndex getLecture() {
        return lecture;
    }

    /*
     * Get tutorial groups(index) of Course
     */
    public ArrayList<CourseIndex> getTutorialIndexes() {
        return tutorialIndexes;
    }

    /*
     * Get lab groups(index) of Course
     */
    public ArrayList<CourseIndex> getLaboratoryIndexes() {

        return laboratoryIndexes;
    }

    /*
     * Get list of registered student of Course
     */
    public ArrayList<StudentInfo> getRegisteredStudents() {

        return registeredStudents;
    }

    /*
     * Get Component weightages of Course
     */
    public CourseWeight getCourseWeightage() {
        return courseWeightage;
    }

    /**
     * Set methods for Instances
     */

    /*
     * Set Professor ID of Course
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    /*
     * Set Course code
     */
    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    /*
     * Set Course name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /*
     * Set max capacity of Course
     */
    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity < 0 || maxCapacity == 0) {
            maxCapacity = 0;
        } else {
            this.maxCapacity = maxCapacity;
        }
    }

    /*
     * Set Course AU
     */
    public void setCourseAU(int courseAU) {
        this.courseAU = courseAU;
    }

    /*
     * Set Lecture to Course
     */
    public void setLecture(CourseIndex lecture) {
        this.lecture = lecture;
    }

    /*
     * Set tutorial group to Course; assigned as an INDEX
     */
    public void setTutorialIndexes(ArrayList<CourseIndex> tutorialIndexes) {
        this.tutorialIndexes = tutorialIndexes;
    }

    /*
     * Set tutorial group vacancy
     */
    public void setTutVacancy(int index) {
        tutorialIndexes.get( index ).setVacancy();
    }

    /*
     * Set lab group to Course; assigned an as INDEX
     */
    public void setLaboratoryIndexes(ArrayList<CourseIndex> laboratoryIndexes) {
        this.laboratoryIndexes = laboratoryIndexes;
    }

    /*
     * Set lab group vacancy
     */
    public void setLabVacancy(int index) {
        laboratoryIndexes.get( index ).setVacancy();
    }

    /*
     * Set registered students list of Course
     */
    public void setRegisteredStudents(ArrayList<StudentInfo> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    /*
     * Set Course weightages of Course
     */
    public void setCourseWeightage(CourseWeight courseWeightage) {
        this.courseWeightage = courseWeightage;
    }

    /***
     * Checkers for Course class
     */

    /*
     * Check if student is registered into Course
     */
    public boolean isStudentRegisteredInCourse(ArrayList<StudentInfo> student) {
        return registeredStudents.contains( student );
    }

    /*
     * Check if Course is valid
     * Course is valid iff total weightage of Exam + Couse work + total number if course work is not equals to 0
     */
    public boolean isCourseValidatable() {

        if (weightage.getExamination() != null && weightage.getCourseWork() != null &&
                weightage.getNumberOfCourseWork() >= 0) {
            return true;
        } else {
            return false;
        }
    }
}

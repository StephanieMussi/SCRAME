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
   // static CourseWeight weightage;
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
    private ArrayList<CourseIndex> tutorial;
    /*
     * Lab session of a course; number of lab is based on Indexes of Course
     * i.e Index lab group SS1(Index 1101), SS2(Index 1102)
     */
    private ArrayList<CourseIndex> laboratory;
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
    }


    /***
     * Adders
     */

    /*
     * Adding tutorial indexes into Course
     * capacity parameter stores total number of students in each group
     */
    public void addLecture(int capacity){
        lecture = new CourseIndex(capacity);
    }

    public void addTutorial(int[] capacity) {
        int i = 0;
        for (i = 0; i < capacity.length; i++) {
            CourseIndex index = new CourseIndex( i, capacity[i] );
            tutorial.add( index );
        }
    }

    /*
     * Adding lecture indexes into Course
     * capacity parameter stores total number of students
     */
    public void addLab(int[] capacity) {
        int i = 0;
        for (i = 0; i < capacity.length; i++) {
            CourseIndex index = new CourseIndex( i, capacity[i] );
            laboratory.add( index );
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
    public ArrayList<CourseIndex> getTutorialIndex() {
        return tutorial;
    }

    /*
     * Get lab groups(index) of Course
     */
    public ArrayList<CourseIndex> getLaboratoryIndex() {

        return laboratory;
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
        this.tutorial = tutorialIndexes;
    }

    /*
     * Set tutorial group vacancy
     */
    public void setTutVacancy(int index) {
        tutorial.get( index ).setVacancy();
    }

    /*
     * Set lab group to Course; assigned an as INDEX
     */
    public void setLaboratoryIndexes(ArrayList<CourseIndex> laboratoryIndexes) {
        this.laboratory = laboratoryIndexes;
    }

    /*
     * Set lab group vacancy
     */
    public void setLabVacancy(int index) {
        laboratory.get( index ).setVacancy();
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
     * Check if Course is valid
     * Course is valid iff total weightage of Exam + Couse work + total number if course work is not equals to 0
     */
    public boolean isCourseValidatable() {
        double examWeight = courseWeightage.getExamination().getTotalWeightage();
        ArrayList<Assessment> courseworkWeight = courseWeightage.getCourseWork();
        double totalCourseWorkW = 0;
        for(int i =0; i< courseworkWeight.size(); i++){
            totalCourseWorkW += courseworkWeight.get(i).getTotalWeightage();
        }
        if( (examWeight == 100 && totalCourseWorkW == 0) || totalCourseWorkW == 100 )
            return true;
        else return false;
    }
}

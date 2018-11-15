/**
 * Course class (Model)
 * Stores and Retrieves Course Information - Adding Lectures, Tutorials and Lab into a Course
 * Manages Course assessments and enrolled students
 */

import java.io.Serializable;
import java.util.ArrayList;
public class Course implements Serializable {

    private int professorId;

    private int courseCode;

    private String courseName;

    private int courseAU;

    private CourseIndex lecture;
    /**
     * Tutorial session of a course; number of tutorial is based on Indexes of Course
     * i.e Index Tutorial group SS1(Index 1101), SS2(Index 1102)
     */
    private ArrayList<CourseIndex> tutorial;
    /**
     * Lab session of a course; number of lab is based on Indexes of Course
     * i.e Index lab group SS1(Index 1101), SS2(Index 1102)
     */
    private ArrayList<CourseIndex> laboratory;
    /**
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
        tutorial = new ArrayList<CourseIndex>();
        laboratory = new ArrayList<CourseIndex>();

    }



    /**
     * Adding tutorial indexes into Course
     * capacity parameter stores total number of students in each group
     */
    public void addLecture(int index, int capacity) {
        lecture = new CourseIndex( index, capacity );
    }

    public void addTutorial(int[] capacity) {
        int i = 0;
        for (i = 0; i < capacity.length; i++) {
            CourseIndex index = new CourseIndex( i + courseCode * 1000, capacity[i] );
            tutorial.add( index );
        }
    }

    /**
     * Adding lecture indexes into Course
     * capacity parameter stores total number of students
     */
    public void addLab(int[] capacity) {
        int i = 0;
        for (i = 0; i < capacity.length; i++) {
            CourseIndex index = new CourseIndex( i + courseCode * 1000, capacity[i] );
            laboratory.add( index );
        }
    }


    /**
     * get Professor ID in charged of Course
     */
    public int getProfessorId() {
        return professorId;
    }

    /**
     * Get Course code; CZ2002 -> 2002
     */
    public int getCourseCode() {
        return courseCode;
    }

    /**
     * Get Course Name; Object Oriented and Design Programming
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Get Course AU
     */
    public int getCourseAU() {
        return courseAU;
    }

    /**
     * Get lecture from Course
     */
    public CourseIndex getLecture() {
        return lecture;
    }

    /**
     * Get tutorial groups(index) of Course
     */
    public ArrayList<CourseIndex> getTutorialIndex() {
        return tutorial;
    }

    /**
     * Get lab groups(index) of Course
     */
    public ArrayList<CourseIndex> getLaboratoryIndex() {
        return laboratory;
    }

    /**
     * Get Component weightages of Course
     */
    public CourseWeight getCourseWeightage() {
        return courseWeightage;
    }

    /**
     * Set methods for Instances
     */

    /**
     * Set Course weightages of Course
     */
    public void setCourseWeightage(CourseWeight courseWeightage) {
        this.courseWeightage = courseWeightage;
    }



    /**
     * Checkers for Course class
     */

    /**
     * Check if Course is valid
     * Course is valid iff total weightage of Exam + Couse work + total number if course work is not equals to 0
     */
    public boolean isCourseValidatable() {
        double examWeight;
        try {
            examWeight = courseWeightage.getExamination().getTotalWeightage();
        } catch (NullPointerException e) {
            return false;
        }
        ArrayList<Assessment> courseworkWeight = courseWeightage.getCourseWork();
        double totalCourseWorkW = 0;
        for (int i = 0; i < courseworkWeight.size(); i++) {
            totalCourseWorkW += courseworkWeight.get( i ).getTotalWeightage();
        }
        if (totalCourseWorkW + examWeight - 100 < 0.000000001)
            return true;
        else {
            System.out.println( "assessment weightage assignment encountered error, please enter again!" );
            return false;
        }
    }

    /**
     * Check if index exist
     * @param index
     * @return boolean true->exist false->not
     */
    public boolean checkInExist(int index) {
        for (int i = 0; i < tutorial.size(); i++) {
            CourseIndex in = tutorial.get( i );
            if (in.getIndex() == index)
                return true;
        }
        if (lecture.getIndex() == index)
            return true;

        return false;
    }

    /**
     * print all index
     */
    public void printIndex() {
        System.out.println( "The lecture index is: " + lecture.index );
        System.out.println( "the lecture vacancy is: " + checkInVacancy( lecture ) );
        if (tutorial.size() > 0) {
            System.out.println( "The tutorial or lab indices are:" );
            for (int i = 0; i < tutorial.size(); i++) {
                System.out.print( tutorial.get( i ).index + "\t" );
                System.out.print( checkInVacancy( tutorial.get( i ) ) + " / " + tutorial.get( i ).getCapacity() );
                System.out.println();
            }
        }
    }

    /**
     * check vacancy
     * @param index
     * @return
     */
    public int checkInVacancy(CourseIndex index) {
        if (index != null) {
            return index.getVacancy();
        } else {
            System.out.println( "the index doesn't exist!" );
            return 0;
        }
    }

    /**
     * getter
     * @param in
     * @param isTut
     * @return CourseIndex
     */
    public CourseIndex getIndexByIn(int in, boolean isTut) {
        if (isTut) {
            CourseIndex index = null;
            for (int i = 0; i < tutorial.size(); i++) {
                index = tutorial.get( i );
                if (index.getIndex() == in)
                    return index;
            }
        }
        return null;
    }

    /**
     * getter
     * @param in
     * @return
     */
    public CourseIndex getIndexByIn(int in) {
        CourseIndex index = null;
        for (int i = 0; i < laboratory.size(); i++) {
            index = laboratory.get( i );
            if (index.getIndex() == in)
                return index;
        }
        return null;
    }


}
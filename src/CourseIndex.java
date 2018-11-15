/***
 * Class tagged index to Courses
 * Each Index consist of a Lecture, Tutorial, Lab
 * Handles information regarding Lectures, Tutorials and Lab works
 */

import java.io.Serializable;

public class CourseIndex implements Serializable {

    protected int index;

    protected Course course;

    protected int vacancy;

    protected int capacity;

    protected String name;


    /**
     * Constructor class for CourseIndex
     * @param index
     * @param capacity
     */
    public CourseIndex(int index, int capacity) {
        this.index = index;
        this.capacity = capacity;
        this.vacancy = capacity;
        this.course = null;
    }


    /***
     * Getters for Instances
     * @return index
     */
    public int getIndex() {

        return index;
    }


    /**
     * @return course
     */
    public Course getCourse() {

        return course;
    }

    /**
     * @return vacancy
     */
    public int getVacancy() {

        return vacancy;
    }

    /**
     * @return capacity
     */
    public int getCapacity() {

        return capacity;
    }

    /**
     * @return name
     */
    public String getName() {

        return name;
    }

    /**
     * Setters for instances
     * Whenever set, vacancy minus by 1
     */
    public void setVacancy() {

        vacancy--;
    }

}
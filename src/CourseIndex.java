import java.io.Serializable;

/***
 * Class tagged index to Courses
 * Each Index consist of a Lecture, Tutorial, Lab
 * Handles information regarding Lectures, Tutorials and Lab works
 */
public class CourseIndex implements Serializable {
    /*
     * Index of Course
     */
    protected int index;
    /*
     * Tagged Index to Course
     */
    protected Course course;
    /*
     * Lesson's Vacancies
     */
    protected int vacancy;
    /*
     * Lesson's Capacity
     */
    protected int capacity;
    /*
     * Student's Name tagged to Course (enrolled)
     */
    protected String name;
    // Create a list for enrolled students to index?

    /***
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
     * @return
     */

    /*
     * Get index of Course
     */
    public int getIndex() {

        return index;
    }

    /*
     * Get Course that belongs to current index
     */
    public Course getCourse() {

        return course;
    }

    /*
     * Get vacancies available of Index
     */
    public int getVacancy() {

        return vacancy;
    }

    /*
     * Get capacity of Index
     * Assuming capacity of a lesson is 20
     */
    public int getCapacity() {

        return capacity;
    }

    /*
     * Get student enrolled into this Index
     */
    public String getName() {

        return name;
    }

    /***
     * Setters for instances
     */

    /*
     * Set Vacancy of Course
     * Whenever set, vacancy minus by 1
     */
    public void setVacancy() {

        vacancy--;
    }

}
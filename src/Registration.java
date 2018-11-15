import java.io.Serializable;

//Assuming you know the student ID and the course & index he/she wants to enroll
//Need something to call the IndexCourse entity.
public class Registration implements Serializable {
    private int student, index, course;


    /**
     * @param course
     * @param student
     * @param index
     */
    public Registration(int course, int student, int index) {
        this.course = course;
        this.student = student;
        this.index = index;
    }


    /**
     * @return student
     */
    public int getStudent() {
        return student;
    }

    /**
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return course
     */
    public int getCourse() {
        return course;
    }


    /**
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @param course
     */
    public void setCourse(int course) {
        this.course = course;
    }

}
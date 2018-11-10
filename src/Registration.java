import java.io.Serializable;

//Assuming you know the student ID and the course & index he/she wants to enroll
//Need something to call the IndexCourse entity.
public class Registration implements Serializable {
    private int student, index, course;
    private String studentName;


    //Constructor
    public Registration(int course, int student, int index, String studentName) {
        this.course = course;
        this.student = student;
        this.index = index;
        this.studentName = studentName;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public int getStudent() {
        return student;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getCourse() {
        return course;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
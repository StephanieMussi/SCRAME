import java.io.Serializable;

//Assuming you know the student ID and the course & index he/she wants to enroll
//Need something to call the IndexCourse entity.
public class Registration implements Serializable {
    private int student, index;
    private String course, studentName;

    //Constructor
    public Registration(String course, int student, int index, String studentName) {
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

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
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

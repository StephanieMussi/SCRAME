import java.util.ArrayList;
import java.util.List;

/***
 * Course Database Class
 * Handles Course data of the Database
 */
public class CourseDB {
    /*
     * Create an ArrayList for Course
     */
    private List<Course> courseList = new ArrayList<Course>();

/*    public CourseDB() {
        initialize();
    }

    public void initialize() {
        courseList = new ArrayList<Course>();
        // if FileFound
        loadCourse();
        // else create course.txt
    }

    public void loadCourse() {
        // retrieve courses from file
        courseList = SerializeDB.readSerializedObject( "course.rtf" );
    }
*/

    /*
     * Parse a new "course" into the database
     */
    public void addCourse(Course newCourse) {
        courseList.add( newCourse );
    }


    /***
     * Getters for Course
     */

    /*
     * Get Course list
     */
    public List<Course> getCourseList() {

        return this.courseList;
    }

    /*
     * Obtain a Object by courseCode - Course
     */
    public Course getCourse(int courseCode) {
        if (courseList.size() == 0) { //Return null if course list is empty
            return null;
        } else {
            for (int i = 0; i < courseList.size(); i++) {
                if (courseList.get( i ).getCourseCode() == courseCode) {
                    return courseList.get( i );
                }
            }
        }
        return null;
    }

    /*
     * Obtain a Object by courseName - Course
     */
    public Course getCourseByName(String courseName) {
        if (courseList.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < courseList.size(); i++) {
                if (courseList.get( i ).getCourseName() == courseName) {
                    return courseList.get( i );
                }
            }
        }
        return null;
    }
/*
    public void saveCourseList() {

        SerializeDB.writeSerializedObject( "course.rtf", courseList );
    }
*/
}

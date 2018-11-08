import java.util.ArrayList;
import java.util.List;

/***
 * Course manager class (Controller)
 * Handles and manages information of Course
 */
public class CourseMgr {
    /***
     * Call
     * Course class
     * CourseDB class
     * CourseWeight class
     */

    /*
     * Initialise Course database; CourseDB
     */
    private CourseDB courseDB = new CourseDB();

    /*
     * Adders
     */
    public void addCourse(int profId, int courseCode, String courseName, int courseAU) {
        //Create a new Course Object
        Course newCourse = new Course( profId, courseCode, courseName, courseAU );
        courseDB.addCourse( newCourse );
    }


    /***
     * Getters
     */

    /*
     * Retrieves all course by their code i.e CZ2002 - Objected Oriented Design and Programming
     * Obtain 2002 from course list
     * Stores 2002 into an int array list
     */
    public int[] getCourseByCode() {
        List<Course> courseList = courseDB.getCourseList();
        int[] courseCodeList = new int[courseList.size()];
        if (courseList.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < courseList.size(); i++) {
                courseCodeList[i] = courseList.get( i ).getCourseCode();
            }
        }
        return courseCodeList;
    }


    /*
     * Retrieves all course by their Name i.e CZ2002 - Objected Oriented Design and Programming
     * Obtain Objected Oriented Design and Programming from course list
     * Stores  Objected Oriented Design and Programming into an String array list
     */
    public String[] getCourseByName() {

        List<Course> courseList = courseDB.getCourseList();
        String[] courseNameList = new String[courseList.size()];
        if (courseList.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < courseList.size(); i++) {
                courseNameList[i] = courseList.get( i ).getCourseName();
            }
            return courseNameList;
        }
    }

    /***
     * Print Course list
     */
    public void printCourseList() {
        List<Course> courseList = courseDB.getCourseList();

        System.out.printf( "%s\t%s\t\t%s\t\t%s\n", "Professor ID", "AU", "Course Code", "Course Name" );

        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf( "%12d\t%2d\t%15d\t\t%-20s\n",
                    courseList.get( i ).getProfessorId(),
                    courseList.get( i ).getCourseAU(),
                    courseList.get( i ).getCourseCode(),
                    courseList.get( i ).getCourseName() );
        }
    }

    public void printCourseCodeList() {
        List<Course> courseCodeList = courseDB.getCourseList();

        System.out.printf( "%s\t\t%s\n", "AU", "Course code" );
        for (int i = 0; i < courseCodeList.size(); i++) {
            System.out.printf( "%2d\t\t%-11d\n", courseCodeList.get( i ).getCourseAU(),
                    courseCodeList.get( i ).getCourseCode() );
        }
    }

    public void printCourseNameList() {
        List<Course> courseNameList = courseDB.getCourseList();

        System.out.printf( "%s\t\t%s\n", "AU", "Course name" );
        for (int i = 0; i < courseNameList.size(); i++) {
            System.out.printf( "%2d\t\t%-20s\n", courseNameList.get( i ).getCourseAU(),
                    courseNameList.get( i ).getCourseName() );
        }
    }

    /*
     * Get Exam weightage by Course code
     */
    public Assessment getExamWeight(int courseCode){
        Course course = courseDB.getCourse( courseCode );
        return Course.weightage.getExamination();
    }

    /*
     * Get Course work weightage by Course code
     */
    public ArrayList<Assessment> getCourseworkWeightByCode(int courseCode) {
        Course course = courseDB.getCourse( courseCode );
        return Course.weightage.getCourseWork();
    }

    /*
     * Get number of Course work by Course code
     */
    public int getNumOfCourseworkInCourseByCode(int courseCode) {
        Course course = courseDB.getCourse( courseCode );
        return Course.weightage.getNumberOfCourseWork();
    }

    /***
     * Verifier for Course
     */
    public boolean isCourseExistInDB(int courseCode) {

        Course course = courseDB.getCourse( courseCode );
        if (courseDB.getCourseList().size() == 0) { //Course list is empty
            return false;
        } else if (course == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isCourseReadyForRegistration(int courseCode) {

        Course course = courseDB.getCourse( courseCode );
        if (courseDB.getCourseList().size() == 0) { //Course list is empty
            return false;
        } else if (course.isCourseValidatable() && isCourseExistInDB( courseCode )) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        CourseMgr cmgr = new CourseMgr();
        cmgr.addCourse( 01, 2002, "Object Oriented Design and Programming", 3 );
        cmgr.addCourse( 02, 2001, "Algorithm", 3 );
        cmgr.addCourse( 03, 2005, "Operating System", 3 );
        cmgr.addCourse( 04, 2006, "Software Engineering", 3 );
        cmgr.addCourse( 05, 2007, "Introduction to Database", 3 );
        cmgr.printCourseList();
        System.out.println();
        cmgr.getCourseByCode();
        cmgr.printCourseCodeList();
        System.out.println();
        cmgr.getCourseByName();
        cmgr.printCourseNameList();
    }
}


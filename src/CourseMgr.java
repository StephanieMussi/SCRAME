import java.util.List;

/***
 * Course Manager class (Controller)
 * Handles and manages the information of Course
 */
public class CourseMgr {

    /*
     * Call and create a new CourseDB
     */
    static CourseDB courseDB;

    public CourseMgr() {
        courseDB = new CourseDB();
    }

    /*
     * Adding a new Course
     */
    public void addCourse(int courseCode, String Cname, int profId /*int[] tutorialCapacity, int[] labCapacity*/) {

        // Create a new course object
        Course newCourse = new Course(courseCode, Cname, profId);

        /* Adding Tutorials and Laboratory work to newly created Course;
        newCourse.addTutorials( tutorialCapacity );
        newCourse.addLab( labCapacity );*/

        // Adding newly created course into Course Database
        courseDB.addCourse( newCourse );

    }
    /*
     * Print out Course List
     */
    public void printCourseList() {

        List<Course> courseList = courseDB.getCourseList();
        System.out.printf("%s\t%20s\t%s\n", "Course Code", "Course Name", "Professor ID");

        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("%11d\t%20s\t%11d\n",
                    courseList.get(i).getCourseCode(), courseList.get(i).getCourseName(), courseList.get(i).getProfId());
        }
    }

    /*
     *
    public int[] checkVacancyByCourseCode(int courseCode, IndicesOfIndex indexNum) {
        Course code = courseDB.getCourse( courseCode );

    }

    public void checkAvailableVacancyOfIndex(int courseCode) {
        Course code = courseDB.getCourse( courseCode );

        // check available solts from courseList.get(i), iterate indexNum, find the index, check vacancy
        // print the result
    }
    *
    */

    /*
     * Retrieves all course by their code i.e CZ2002 - Objected Oriented Design and Programming
     * Obtain 2002 from course list
     * Stores 2002 into an int array list
     */
    public int[] getCoursesByCode() {

        List<Course> courseList = courseDB.getCourseList();
        int[] courseCodeList = new int[courseList.size()];
        if (courseList.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < courseList.size(); i++) {
                courseCodeList[i] = courseList.get( i ).getCourseCode();
            }
            return courseCodeList;
        }
    }

    /*
     * Retrieves all course by their Name i.e CZ2002 - Objected Oriented Design and Programming
     * Obtain Objected Oriented Design and Programming from course list
     * Stores  Objected Oriented Design and Programming into an String array list
     */
    public String[] getCoursesByName() {

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

    /*
     * Obtain Exam weightage and Coursework weight of Course
     * Obtain number of Coursework of a Course
     * By Course Code / Name
     */
    public double getExamWeightByCode(int courseCode) {
        Course course = courseDB.getCourse( courseCode );
        return course.getExamWeight();
    }

    public double getExamWeightByName(String Cname) {
        Course course = courseDB.getCourseByName( Cname );
        return course.getExamWeight();
    }

    public double[] getCourseworkWeightByCode(int courseCode) {
        Course course = courseDB.getCourse( courseCode );
        return course.getCourseWorkWeight();
    }

    public double[] getCourseworkWeightByName(String Cname) {
        Course course = courseDB.getCourseByName( Cname );
        return course.getCourseWorkWeight();
    }

    public int getNumOfCourseworkInCourseByCode(int courseCode) {
        Course course = courseDB.getCourse( courseCode );
        return course.getNumberOfCourseWork();
    }

    public int getNumOfCourseWorkInCourseByName(String Cname) {
        Course course = courseDB.getCourseByName( Cname );
        return course.getNumberOfCourseWork();
    }

    /***
     * Setters
     */
    // Sets weight-ages of assessment of a Course based on Course Code
    public void setCourseWeightageByCode(int courseCode, double examWeight, double[] courseWorkWeight) {
        Course course = courseDB.getCourse( courseCode );
        course.setWeightage( examWeight, courseWorkWeight );
    }

    // Sets weight-ages of assessment of a Course based on Course Name
    public void setCourseWeightageByName(String Cname, double examWeight, double[] courseWorkWeight) {
        Course course = courseDB.getCourseByName( Cname );
        course.setWeightage( examWeight, courseWorkWeight );
    }

    /*
     * Checkers by course code (Verifiers)
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

    public static void main(String[] args){

        CourseDB CourseDBTest = new CourseDB();
        CourseMgr CourseTest = new CourseMgr();

        CourseTest.addCourse(2002, "OODP", 1001);
        CourseTest.addCourse( 2001, "Algorithm", 1002);
//        CourseTest.setCourseWeightageByCode(2002, 70, );
        CourseTest.printCourseList();


    }

}

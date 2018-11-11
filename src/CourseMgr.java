import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    Scanner scan = new Scanner( System.in );
    private CourseDB courseDB = new CourseDB();


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

    // Get Exam weightage by Course code

    public Assessment getExamWeight(int courseCode) {
        Course course = courseDB.getCourse( courseCode );
        return course.getCourseWeightage().getExamination();
    }


    //Get Course work weightage by Course code
    public ArrayList<Assessment> getCourseworkWeight(int courseCode) {
        Course course = courseDB.getCourse( courseCode );
        return course.getCourseWeightage().getCourseWork();
    }


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

    public void addCourse() {
        int courseCode = -1, professorId = -1, courseAU = -1;
        String courseName = null;
        boolean success = false, success2 = false, success3 = false, success4 = false;
        //enter sid, loop avoid collision

        System.out.println( "Please enter Course code to be added (It should be unique):" );
        do {
            try {
                courseCode = scan.nextInt();
                if (isCourseExistInDB( courseCode )) {
                    throw new isDuplicatesException( "Course" );
                }
                success = true;
            } catch (isDuplicatesException eID) {
                System.out.println( eID.getMessage() );
            }
        } while (!success);
        /*
            courseCode = scan.nextInt();
            if (isCourseExistInDB( courseCode ))
                break;
            else {
                System.out.print( "course ID alr exists, please enter another one" );
                continue;
            }
        } while (true);*/

        //enter courseInfo
        System.out.println( "Adding Course " + courseCode + ", please enter following information:" );
        System.out.println( "Enter Course name:" );
        do {
            try {
                courseName = scan.next();
                if (courseName.matches( (".*\\d+.*") )) {
                    throw new isInvalidInputException( "Alphabets only !" );
                }
                success2 = true;
            } catch (isInvalidInputException e) {
                System.out.println( e.getMessage() );
            }
        } while (!success2);
        success2 = false;

        System.out.println( "Enter Professor ID:" );
        professorId = scan.nextInt();
        System.out.println( "Enter Course AU:" );
        courseAU = scan.nextInt();
        Course newCourse = new Course( professorId, courseCode, courseName, courseAU );
        courseDB.addCourse( newCourse );

        // add sessions for this course
        int capLec, num;
        int[] capacity;
        System.out.println( "What type of lessons does this course have?" );
        System.out.println( "1: Only Lectures\n2: Lecture and Tutorials\n3: Lecture and Tutorials and Labs" );
        int courseType = scan.nextInt();

        switch (courseType) {
            case 1:
                System.out.println( "Enter the capacity for the lecture:" );
                capLec = scan.nextInt();
                newCourse.addLecture( capLec );
                break;
            case 2:
                System.out.println( "Enter the number of tutorial sessions" );
                num = scan.nextInt();
                System.out.println( "Enter the capacity of each session:" );
                int cap = scan.nextInt();
                capacity = new int[num];
                for (int i = 0; i < num; i++)
                    capacity[i] = cap;
                newCourse.addLecture( cap * num );
                newCourse.addTutorial( capacity );
                break;
            case 3:
                System.out.println( "Enter the number of tutorial/lab sessions" );
                num = scan.nextInt();
                System.out.println( "Enter the capacity of each session:" );
                int cap2 = scan.nextInt();
                capacity = new int[num];
                for (int i = 0; i < num; i++)
                    capacity[i] = cap2;
                newCourse.addLecture( cap2 * num );
                newCourse.addTutorial( capacity );
                newCourse.addLab( capacity );
                break;
        }
        setAssessment( courseCode );

    }

    public void setAssessment(int courseCode) {
        Assessment exam;
        ArrayList<Assessment> coursework = new ArrayList<>();
        Course thisCourse = courseDB.getCourse( courseCode );
        CourseWeight thisCourseW;
        int num;
        do {
            System.out.println( "Setting course assessment..." );
            System.out.println( "Please enter exam weightage(%):" );
            double examWeight = scan.nextDouble();
            exam = new Assessment( "exam", examWeight );
            if (examWeight != 100) {
                System.out.println( "Enter number of CAs:" );
                num = scan.nextInt();
                for (int i = 0; i < num; i++) {
                    System.out.println( "Please enter coursework description(%):" );
                    String descrip = scan.next();
                    System.out.println( "Please enter coursework weightage(%):" );
                    double cwWeight = scan.nextDouble();
                    coursework.add( new Assessment( descrip, cwWeight ) );
                }
            }
            thisCourse.setCourseWeightage( new CourseWeight( exam, coursework ) );
        } while (!thisCourse.isCourseValidatable());
    }


    public static void main(String[] args) {
        CourseMgr cmgr = new CourseMgr();
        /*cmgr.addCourse( 01, 2002, "Object Oriented Design and Programming", 3 );
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
        cmgr.printCourseNameList();*/
        cmgr.addCourse();
    }
}


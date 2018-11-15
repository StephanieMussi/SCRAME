import java.util.ArrayList;
import java.util.InputMismatchException;
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
    Scanner scan;
    //Scanner scan = new Scanner( System.in );
    private SerializeToDatabaseInterface serializeDb;
    public CourseDB courseDB;

    /***
     * Constructor class for Course Manager
     * @param scan
     * @param serializeDb
     */
    public CourseMgr(Scanner scan, SerializeToDatabaseInterface serializeDb) {
        this.scan = scan;
        this.serializeDb = serializeDb;
        this.courseDB = new CourseDB(this.serializeDb);
    }

    /***
     * Getters
     */


    public void printCourseList() {
        List<Course> courseList = courseDB.getCourseList();

        System.out.printf( "%s\t%s\t\t%s\t\t%-20s\n", "Coordinator Name", "AU", "Course Code", "Course Name" );

        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf( "%16s\t%2d\t%15d\t\t%-25s\n",
                    ProfessorMgr.findProfByPid( courseList.get( i ).getProfessorId() ).getName(),
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

    public void addCourse() {
        int courseCode = -1, professorId = -1, courseAU = -1;
        String courseName = null;
        boolean success = false, success2 = false;
        boolean success3 = false, success4 = false;
        //enter sid, loop avoid collision

        do {
            System.out.println( "Please enter Course Code (digits from 0 - 999):" );
            try {
                courseCode = scan.nextInt();
                if (courseCode < 0 || courseCode > 999) {
                    throw new isInvalidInputException( "Course Code" );
                }
                if (isCourseExistInDB( courseCode )) {
                    throw new isDuplicatesException( "Course Code" );
                }
                success = true;
            } catch (isInvalidInputException e) {
                System.out.println( "Please enter a valid Course Code" );
            } catch (isDuplicatesException eID) {
                System.out.println( eID.getMessage() );
            } catch (InputMismatchException e) {
                System.out.println( "Please enter integer only." );
                scan.nextLine();
            }
        } while (!success);
        success = false;

        //enter courseInfo
        System.out.println( "Adding Course " + courseCode + ", please enter following information:" );
        System.out.println( "Enter Course name:" );
        scan.nextLine();
        do {
            try {
                courseName = scan.nextLine();
                if (!courseName.matches( "([a-zA-Z ]+)" )) {
                    throw new isInvalidInputException( "Alphabets only for Course Name!" );
                }
                success2 = true;
            } catch (isInvalidInputException e) {
                System.out.println( e.getMessage() );
            }
        } while (!success2);
        success2 = false;

        System.out.println( "Enter Professor ID:" );
        do {
            try {
                professorId = scan.nextInt();
                if (!(professorId >= 0 && ProfessorMgr.isProfInDB( professorId ))) {
                    throw new isInvalidInputException( "Professor ID" );
                }
                success = true;
            } catch (isInvalidInputException e) {
                System.out.println( "Please enter a valid Professor ID. " );
            } catch (InputMismatchException e) {
                System.out.println( " Please enter integer only. " );
                scan.nextLine();
            }
        } while (!success);
        // success = false;
        System.out.println( "Enter Course AU:" );
        do {
            try {
                courseAU = scan.nextInt();
                if (courseAU < 0) {
                    throw new isInvalidInputException( "Course AU" );
                }
                success2 = true;
            } catch (isInvalidInputException e) {
                System.out.println( "Please enter a valid AU number. " );
            } catch (InputMismatchException e) {
                System.out.println( "Please enter integer." );
                scan.nextLine();
            }
        } while (!success2);
        //success2 = false;
        Course newCourse = new Course( professorId, courseCode, courseName, courseAU );

        // add sessions for this course
        int courseType = -1;
        int capLec = -1, cap = -1, num = -1;
        int[] capacity;
        System.out.println( "What type of lessons does this course have?" );
        System.out.println( "1: Only Lectures\n2: Lecture and Tutorials\n3: Lecture and Tutorials and Labs" );
        do {
            try {
                courseType = scan.nextInt();
                if (courseType < 1 || courseType > 3) {
                    throw new isInvalidInputException( "Course Type." );
                }
                success3 = true;
            } catch (isInvalidInputException e) {
                System.out.println( "Please enter '1', '2' or '3' only" );
            } catch (InputMismatchException e) {
                System.out.println( "Please enter integer only." );
                scan.nextLine();
            }
        } while (!success3);
        int i = 0;

        boolean correct;
        switch (courseType) {
            case 1:
                System.out.println( "Enter the capacity for the lecture:" );
                do {
                    correct = true;
                    try {
                        capLec = scan.nextInt();
                    } catch (InputMismatchException e) {
                        correct = false;
                        System.out.println( "Please enter integer only." );
                        scan.nextLine();
                    }
                } while (!correct);
                newCourse.addLecture( newCourse.getCourseCode(), capLec );
                break;
            case 2:
                do {
                    correct = true;
                    try {
                        System.out.println( "Enter the number of tutorial sessions" );
                        num = scan.nextInt();
                        System.out.println( "Enter the capacity of each session:" );
                        cap = scan.nextInt();
                    } catch (InputMismatchException e) {
                        correct = false;
                        System.out.println( "Please enter integer only." );
                        scan.nextLine();

                    }
                } while (!correct);
                capacity = new int[num];
                for (i = 0; i < num; i++)
                    capacity[i] = cap;
                newCourse.addLecture( newCourse.getCourseCode(), cap * num );
                newCourse.addTutorial( capacity );
                break;
            case 3:
                do {
                    correct = true;
                    try {
                        System.out.println( "Enter the number of tutorial/lab sessions:" );
                        num = scan.nextInt();
                        System.out.println( "Enter the capacity of each session:" );
                        cap = scan.nextInt();
                    } catch (InputMismatchException e) {
                        correct = false;
                        System.out.println( "Please enter integer only." );
                        scan.nextLine();
                    }
                } while (!correct);

                capacity = new int[num];
                for (i = 0; i < num; i++)
                    capacity[i] = cap;
                newCourse.addLecture( newCourse.getCourseCode(), cap * num );
                newCourse.addTutorial( capacity );
                newCourse.addLab( capacity );
                break;
        }
        setAssessment( courseCode, newCourse );

        // After all assessment weights are set
        courseDB.addCourse( newCourse );

    }

    private void setAssessment(int courseCode, Course thisCourse) {
        Assessment exam;
        ArrayList<Assessment> coursework;
        int num;
        boolean success;
        do {
            success = true;
            try {
                System.out.println( "Setting course assessment..." );
                System.out.println( "Please enter exam weightage(%):" );
                double examWeight = scan.nextDouble();
                exam = new Assessment( "exam", examWeight );
                coursework = new ArrayList<>();
                if (examWeight != 100) {
                    System.out.println( "Enter number of CAs:" );
                    num = scan.nextInt();
                    System.out.println( "Breaking down CA's weightage.. Enter each CA so they add up to 100% in total:" );

                    double sumCaWe = -1;
                    String[] temdescrip = new String[num];
                    Double[] temwei = new Double[num];
                    do {
                        try {
                            sumCaWe = 0;
                            for (int i = 0; i < num; i++) {
                                System.out.println( "Please enter coursework description for CA " + (i + 1) + "(string):" );
                                String descrip = scan.next();
                                temdescrip[i] = descrip;
                                scan.nextLine();

                                System.out.println( "Please enter coursework weightage(%):" );
                                double cwWeight = scan.nextDouble();
                                temwei[i] = cwWeight;
                                scan.nextLine();

                                sumCaWe += cwWeight;
                            }
                            if (sumCaWe - 100 > 0.00001 || 100 - sumCaWe > 0.00001)
                                throw new isInvalidInputException( "The total sum of CA weightage is 100.\n" +
                                        "The current weightage sum is not 100." );

                        } catch (isInvalidInputException e) {
                            System.out.println( e.getMessage() );
                        }

                    } while (sumCaWe - 100 > 0.00001 || 100 - sumCaWe > 0.00001);

                    for (int i = 0; i < num; i++) {
                        coursework.add( new Assessment( temdescrip[i], temwei[i] * (1 - examWeight / 100) ) );
                    }

                }
                thisCourse.setCourseWeightage( new CourseWeight( exam, coursework ) );
            } catch (Exception e) {
                success = false;
                System.out.println( "Please enter a valid input." );
                scan.nextLine();
            }
        } while (!(success && thisCourse.isCourseValidatable()));
        System.out.println( "Assessment is settled." );
    }


    public void printIndex() {
        int cid = -1;
        do {
            try {
                System.out.println( "Enter course code to check for its index:" );
                cid = scan.nextInt();
                if (CourseDB.getCourse( cid ) == null)
                    throw new isRecordNotFoundException( "This course code" );
            } catch (isRecordNotFoundException e) {
                System.out.println( e.getMessage() );
            }
        } while (CourseDB.getCourse( cid ) == null);
        courseDB.printAllIndex( cid );

    }
}


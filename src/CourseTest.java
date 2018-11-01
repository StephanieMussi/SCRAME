import java.util.Scanner;

public class CourseTest {
    private static Scanner sc = new Scanner( System.in );
    private static CourseMgr courseManager = new CourseMgr();

    public static void main(String[] args) {

        int option;

        System.out.println();
        System.out.println( "1. Add Professor to Course" );
        System.out.println( "2. Add a Course" );
        System.out.println( "3. Enter Course Assessment Component Weightage" );
        System.out.println( "4. Check available slots in a class (Vacancy in a class)" );
        System.out.println( "5. Enter Exam marks" );
        System.out.println( "6. Enter Coursework marks - inclusive of its components" );
        System.out.println( "7. Print Course list" );
        System.out.println( "8. Print Course Statistics" );
        System.out.println( "9. Exit" );
        System.out.println();

        do {
            System.out.println( "Enter Option" );
            option = sc.nextInt();
            switch (option) {
                case 1:
//                  addProfessor();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
//                    enterCourseComponents();
                    break;
                case 4:
//                    checkAvailableVacancy();
                    break;
                case 5:
//                    enterExamMarks();
                    break;
                case 6:
//                    enterCourseworkMarks();
                    break;
                case 7:
                    printCourseList();
                    break;
                case 8:
//                    printCourseStats();
                    break;
                default:
                    System.out.println( "Invalid Choice" );
                    break;
            }
        } while (option != 9);
        /*
        private static void addProfessor(){
            String Pname = null;
            int PID = -1;
            sc.nextLine();
            System.out.println("Enter Professor's name");

            int PID =
        }
        */
    }

    public static void addCourse() {

        System.out.println( "Enter Course code" );
        int courseCode = sc.nextInt();
        System.out.println( "Enter Professor ID" );
        int profId = sc.nextInt();
        System.out.println( "Enter course name" );
        sc.nextLine();
        String Cname = sc.nextLine();
        System.out.println();
        courseManager.addCourse( courseCode, Cname, profId );
    }

    public static void printCourseList() {
        courseManager.printCourseList();
    }

}

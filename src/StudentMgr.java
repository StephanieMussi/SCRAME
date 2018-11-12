import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentMgr {
    /*
     * used for adding student
     * contain reference: StudentDB
     * don't support delete student
     */

    Scanner sc = new Scanner( System.in );
    private static StudentDB studentDB;

    /*
     * constructor
     * instantiate new StudentDB()
     */
    StudentMgr() {
        studentDB = new StudentDB();
        //do some initialization here
    }


    /*
     * required functionality
     * add student into studentDB:
     * studentDB.addStudent(sid, sname);
     */
    public void addStudent() {
        int noOfStu = -1;
        System.out.println( "How many students do you want to add" );
        try {
            noOfStu = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println( "Please enter integers" );
            sc.nextLine();
        }
        System.out.println( "Please enter following information to add a student" );
        for (int i = 0; i < noOfStu; i++) {
            String sname = null;
            int sid = 0;
            boolean success = false, success2 = false;
            do {
                try {
                    System.out.println( "Please enter student ID u want to add:" );
                    sid = sc.nextInt();
                    if (findSbySid( sid ) != null) {
                        throw new isDuplicatesException( "Student ID" );
                    }
                    success = true;
                } catch (isDuplicatesException e) {
                    System.out.println( e.getMessage() );
                } catch (InputMismatchException e) {
                    System.out.println( "Please enter integer values only" );
                    sc.nextLine();
                }
            } while (!success);
            success = false;

            do {
                try {
                    System.out.println( "Please enter student name to be added" );
                    sname = sc.next();
                    if (findSbySname( sname ) != null) {
                        throw new isDuplicatesException( "Student Name" );
                    }
                    if (!sname.matches( "([a-zA-Z ]+)" )) {
                        throw new isInvalidInputException( "Alphabets only for Student Name!" );
                    }
                    success2 = true;
                } catch (isDuplicatesException eName) {
                    System.out.println( eName.getMessage() );
                    sc.nextLine();
                } catch (isInvalidInputException e) {
                    System.out.println( e.getMessage() );
                }

            } while (!success2);
            success2 = false;
            System.out.println( "Student added successfully!" );
            studentDB.addStudent( sid, sname );
            sc.nextLine();
        }
    }


    /*
     * sub functionality
     * int findSidbySname(String name): get sid
     * StudentInfo findSbySname(String name): get object StudentInfo
     */
    public int findSidbySname(String name) {
        return studentDB.getSidbySname( name );
    }

    public StudentInfo findSbySname(String name) {
        return studentDB.getSbySname( name );
    }


    public StudentInfo findSbySid(int id) {
        return studentDB.getSbySid( id );
    }


    /*
     * method for testing
     * print all student info in student.dat
     */
    public void printAll() {
        studentDB.printAll();
    }


}

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 */
public class StudentMgr {
    /**
     * used for adding student
     * contain reference: StudentDB
     * don't support delete student
     */


    //Scanner sc = new Scanner( System.in );
    Scanner scan;
    private SerializeToDatabaseInterface serializeDb;
    public StudentDB studentDB;

    /**
     * constructor
     * instantiate new StudentDB()
     */
    public StudentMgr(Scanner scan, SerializeToDatabaseInterface serializeDb) {
        this.scan = scan;
        this.serializeDb = serializeDb;
        this.studentDB = new StudentDB( this.serializeDb );

    }

    /**
     * add student into studentDB:
     * studentDB.addStudent(sid, sname);
     */

    public void addStudent() {
        int choice = -1;
        int noOfStu = -1;
        System.out.println( "How many students do you want to add?" );
        try {
            noOfStu = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println( "Please enter integers." );
            scan.nextLine();
        }
        System.out.println( "Please enter the following information to add a student:" );
        for (int i = 0; i < noOfStu; i++) {
            String sname = null;
            int sid = 0;
            boolean success = false, success2 = false;
            do {
                try {
                    System.out.println( "Please enter the student ID you want to add:" );
                    sid = scan.nextInt();
                    if (findSbySid( sid ) != null) {
                        throw new isDuplicatesException( "Student ID." );
                    }
                    success = true;
                } catch (isDuplicatesException e) {
                    System.out.println( e.getMessage() );
                } catch (InputMismatchException e) {
                    System.out.println( "Please enter integer values only." );
                    scan.nextLine();
                }
            } while (!success);

            System.out.println( "Please enter the student name to be added:" );
            scan.nextLine();
            do {
                try {
                    sname = scan.nextLine();
                    if (!sname.matches( "([a-zA-Z ]+)" )) {
                        throw new isInvalidInputException( "alphabets only for Student Name!" );
                    }
                    success2 = true;
                } catch (isInvalidInputException e) {
                    System.out.println( e.getMessage() );
                }
            } while (!success2);

            if (findSbySname( sname ) != null) {
                System.out.println( "Student exist in Database already. Would you like to continue to add student?" );
                System.out.println( "Enter Choice \n 1) Yes \n 2) No" );
                choice = scan.nextInt();
                if (choice == 1) {
                    studentDB.addStudent( sid, sname );
                    System.out.println( "Student added successfully!" );
                } else if (choice == 2) {
                    System.out.println( "Student is not added. Returning to Main Menu...\n" +
                            "--------------------------------------------" );
                    return;
                }
            } else {
                System.out.println( "Student added successfully!" );
                studentDB.addStudent( sid, sname );
            }
        }
    }


    /**
     * sub functionality
     *
     * @para
     */

    public StudentInfo findSbySname(String name) {
        return StudentDB.getSbySname( name );
    }


    public StudentInfo findSbySid(int id) {
        return StudentDB.getSbySid( id );
    }


    /**
     * print all student info in student.dat
     */
    public void printAll() {
        studentDB.printAll();
    }


}

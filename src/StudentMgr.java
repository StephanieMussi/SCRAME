import java.util.Scanner;

public class StudentMgr {
    /*
     * used for adding student
     * contain reference: StudentDB
     * don't support delete student
     */

    Scanner scan = new Scanner( System.in );
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
        String sname;
        int sid;
        //enter sid, loop avoid collision
        do {
            System.out.println( "pls enter student id u want to add" );
            sid = scan.nextInt();
            if (studentDB.findSbySid( sid ) == null)
                break;
            else {
                System.out.print( "student ID alr exists, please enter another one" );
                continue;
            }
        } while (true);

        //enter sname, loop avoid collision
        do {
            System.out.println( "pls enter student name u want to add" );
            sname = scan.next();
            if (studentDB.findSbySname( sname ) == null)
                break;
            else {
                System.out.print( "student name alr exists, please enter another one" );
                continue;
            }
        } while (true);

        studentDB.addStudent( sid, sname );
    }


    /*
     * sub functionality
     * int findSidbySname(String name): get sid
     * StudentInfo findSbySname(String name): get object StudentInfo
     */
    public int findSidbySname(String name) {
        return studentDB.findSidbySname( name );
    }

    public StudentInfo findSbySname(String name) {
        return studentDB.findSbySname( name );
    }


    public StudentInfo findSbySid(int id) {
        return studentDB.findSbySid( id );
    }



    /*
     * method for testing
     * print all student info in student.dat
     */
    public void printAll() {
        studentDB.printAll();
    }


    /*
     * main for testing
     */
    public static void main(String[] args) {
        StudentMgr sm = new StudentMgr();
        sm.addStudent();
        sm.addStudent();
        sm.printAll();
    }


}

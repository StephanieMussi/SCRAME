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
        System.out.println("how many students do you want to add");
        int noOfStu = sc.nextInt();
        System.out.println("please enter following information to add a student");
        for(int i= 0; i<noOfStu; i++) {
            String sname;
            int sid;
            //enter sid, loop avoid collision
            do {
                System.out.println("pls enter student id u want to add");
                sid = sc.nextInt();
                if (studentDB.getSbySid(sid) == null)
                    break;
                else {
                    System.out.print("student ID alr exists, please enter another one");
                    continue;
                }
            } while (true);

            //enter sname, loop avoid collision
            do {
                System.out.println("pls enter student name u want to add");
                sname = sc.next();
                if (studentDB.getSbySname(sname) == null)
                    break;
                else {
                    System.out.print("student name alr exists, please enter another one");
                    continue;
                }
            } while (true);
            studentDB.addStudent( sid, sname );
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

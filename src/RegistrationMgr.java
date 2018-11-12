import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrationMgr {

    RegistrationMgr() {
        db = new RegistrationDB();

    }


    RegistrationDB db;
    Scanner sc = new Scanner(System.in);



    //print all registration records
    public void printAllReg() {
        db.printAllReg();
    }

    //Print student name in the class
    public void printRegByIndex(int indexNum) {
        ArrayList<Registration> registrationList = new ArrayList<Registration>();
        System.out.println("List of students in Index: " + indexNum);
        db.printRegByIndex(indexNum);
    }

    //Print student name in the class
    private void printByC(int cid) {
        db.printByC(cid);
    }


    public void printByS(int studentId) {
        db.printByS(studentId);
    }



    //MENU
    public void registrationMenu() {
        int sel = 0;
        int index = -1;
        while (sel<3) {
            System.out.print("\nChoose:");
            System.out.print("\n1. Register student");
            System.out.print("\n2. Print registration list");
            System.out.println("\n3. Quit");
            sel = sc.nextInt();
            switch (sel) {
                case 1:
                    registerS();
                    continue;
                case 2:
                    printReg();
                    continue;
                case 3:
                    System.out.println("exit registration menu...");
                    break;
            }
        }

    }

    //switch 1
    private void registerS() {
        int sel = 0;
        int index = -1;
        int sid = -1;
        int cid = -1;
        //check student exist
        do {
            System.out.println("enter student id:");
            sid = sc.nextInt();
        } while (StudentDB.getSbySid(sid) == null);

        //check course exist
        do {
            System.out.println("enter course code:");
            cid = sc.nextInt();
        } while (CourseDB.getCourse(cid) == null);

        //get course info
        Course c = CourseDB.getCourse(cid);

        //if course only have lec
        if (c.getTutorialIndex() == null && c.getLaboratoryIndex() == null)
        {
            boolean exist = false;
            Registration r = new Registration(cid, sid, c.getCourseCode());
            exist = checkExist(r);
            if (exist)
            {
                System.out.println("registration already exist! added unsuccessfully!");
            }
            else {
                insertVacancy(r);
            }
        }

        //course with lec and lab
        else
        {
            //check index exist
            do {
                System.out.println("enter index:");
                index = sc.nextInt();
            } while (!c.checkInExist(index));

            boolean exist = false;
            Registration r = new Registration(cid, sid, index);
            exist = checkExist(r);
            if (exist)
            {
                System.out.println("registration already exist! added unsuccessfully!");
            }
            else {
                insertVacancy(r);
            }
        }

    }


    //check exist
    //used in registerS
    private boolean checkExist(Registration r) {
        return db.checkExist(r);
    }


    //switch 2
    private void printReg() {
        int sel;
        int index;
        int cid;
        int sid;
        do {
            System.out.println("\n1. Choose by Class Index (tut/lab)\n" +
                    "2. Choose by lecture\n" +
                    "3. Choose by Student\n" +
                    "4. exit printing\n");
            System.out.println("enter your choice:");
            sel = sc.nextInt();
            switch (sel) {
                case 1:
                    System.out.println("Enter Class Index (print by lab/ tut): ");
                    index = sc.nextInt();
                    printRegByIndex(index);
                    continue;
                case 2:
                    System.out.println("Enter course code (print by lec): ");
                    cid = sc.nextInt();
                    printByC(cid);
                    continue;
                case 3:
                    System.out.println("Enter Student ID: ");
                    sid = sc.nextInt();
                    printByS(sid);
                    continue;
                default:
                    break;
            }
            }while (sel<4) ;
    }



    //Register students to their selected course
    //Check for vacancy
    public boolean insertVacancy(Registration r) {
        int vacancy = 0;
        int cid = r.getCourse();
        int index = r.getIndex();
        Course course = CourseDB.getCourse(cid);
        boolean success = false;
        if(cid==index)
        {
            vacancy = course.getLecture().getVacancy();
            if(vacancy>0){
                db.registerStudentForCourse(r);
                success = true;
            }
        }
        else if (course.getTutorialIndex()!=null){
            vacancy = course.getTutinByIn(index).getVacancy();
            if(vacancy>0){
                db.registerStudentForCourse(r);
                success = true;
            }
        }
        else if (course.getLaboratoryIndex()!= null)
        {
            vacancy = course.getLabinByIn(index).getVacancy();
            if(vacancy>0){
                db.registerStudentForCourse(r);
                success = true;
            }
        }
        return success;
    }
}
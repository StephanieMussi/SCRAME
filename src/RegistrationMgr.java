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
            System.out.print("\n2. Print student registration list");
            System.out.println("\n3. Quit to main menu");
            sel = sc.nextInt();
            switch (sel) {
                case 1:
                    registerS();
                    continue;
                case 2:
                    printReg();
                    continue;
                case 3:
                    System.out.println("Exit registration menu...");
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
            try {
                System.out.println("Enter Student ID:");
                sid = sc.nextInt();
                if (StudentDB.getSbySid(sid) == null) {
                    throw new isRecordNotFoundException( "Student ID" );
                }
            } catch (isRecordNotFoundException e) {
                System.out.println( e.getMessage() );
            }
        } while (StudentDB.getSbySid(sid) == null);

        //check course exist
        do {
            try {
                System.out.println("Enter Course Code:");
                cid = sc.nextInt();
                if (CourseDB.getCourse(cid) == null) {
                    throw new isRecordNotFoundException( "Course Code" );
                }
            } catch (isRecordNotFoundException e) {
                System.out.println(e.getMessage());
            }
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
                System.out.println("Registration already exist! Added unsuccessfully!");
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
                try {
                    System.out.println("Enter Index:");
                    index = sc.nextInt();
                    if (!c.checkInExist(index)) {
                        throw new isInvalidIndexException( "again!" );
                    }
                } catch (isInvalidIndexException e) {
                    System.out.println(e.getMessage());
                }
            } while (!c.checkInExist(index));

            boolean exist = false;
            Registration r = new Registration(cid, sid, index);
            exist = checkExist(r);
            if (exist)
            {
                System.out.println("Registration already exist! Added unsuccessfully!");
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
            System.out.println("\n1. Print by Class Index (Tut/Lab)\n" +
                    "2. Print by Lecture\n" +
                    "3. Print by Student\n" +
                    "4. Exit printing\n");
            System.out.println("Enter your choice:");
            sel = sc.nextInt();
            switch (sel) {
                case 1:
                    System.out.println("Enter Class Index (Print by Lab/Tut): ");
                    index = sc.nextInt();
                    printRegByIndex(index);
                    continue;
                case 2:
                    System.out.println("Enter Course Code (Print by Lec): ");
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
        if(cid==index)
        {
            CourseIndex lec =course.getLecture();
            vacancy = lec.getVacancy();
            if(vacancy>0){
                lec.setVacancy();//vacancy--
                db.registerStudentForCourse(r);//register successful
                return true;
            }
        }
        else if (course.getLaboratoryIndex()!= null)
        {
            CourseIndex lab =course.getLabinByIn(index);
            CourseIndex tut =course.getTutinByIn(index);
            vacancy = lab.getVacancy();
            if(vacancy>0){
                //if have lab, must have tut
                lab.setVacancy();
                tut.setVacancy();
                db.registerStudentForCourse(r);
                return true;
            }
        }
        else if (course.getTutorialIndex()!=null){
            CourseIndex tut =course.getTutinByIn(index);
            vacancy = tut.getVacancy();
            if(vacancy>0){
                tut.setVacancy();
                db.registerStudentForCourse(r);
                return true;
            }
        }

        System.out.println("Added unsuccessfully! Index: "+index+" is full!");
        return false;
    }
}

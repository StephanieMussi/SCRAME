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
        System.out.println("List of students in index: " + indexNum);
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
        if (c.getTutorialIndex() == null && c.getLaboratoryIndex().size() == 0)
        {
            boolean exist = false;
            Registration r = new Registration(cid, sid, c.getCourseCode());
            exist = checkExist(r);
            if (exist)
            {
                System.out.println("Registration already exist! Added unsuccessfully!");
            }
            else {
                insertReg(r);
            }
        }

        //course with lec and lab
        else
        {
            //check index exist
            System.out.println("Current index of this course:");
            c.printIndex();
            do {
                try {
                    System.out.println("Enter index:");
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
                insertReg(r);
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
        int index = -1;
        int cid = -1;
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
                    //enter coursecode, check validation
                    do {
                        try {
                            System.out.println("Enter Course Code (for further index entering):");
                            cid = sc.nextInt();
                            if (CourseDB.getCourse(cid) == null) {
                                throw new isRecordNotFoundException( "Course Code" );
                            }
                        } catch (isRecordNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (CourseDB.getCourse(cid) == null);

                    //enter index, check validation
                    do {
                        try {
                            System.out.println("Enter index (print by tut/lab):");
                            index = sc.nextInt();
                            if (!CourseDB.getCourse(cid).checkInExist(index)) {
                                throw new isRecordNotFoundException( "index" );
                            }
                        } catch (isRecordNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (CourseDB.getCourse(cid) == null);

                    //print by tut / lab
                    printRegByIndex(index);
                    continue;
                case 2:
                    do {
                        try {
                            System.out.println("Enter Course Code (print by lec):");
                            cid = sc.nextInt();
                            if (CourseDB.getCourse(cid) == null) {
                                throw new isRecordNotFoundException( "Course Code" );
                            }
                        } catch (isRecordNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (CourseDB.getCourse(cid) == null);
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
    public void insertReg(Registration r) {
        int index = r.getIndex();
        int type = checkVacancyForReg(r);
        if(type <  0)
        {
            System.out.println("Added unsuccessfully! Index: "+index+" is full!");
        }
        else
        {
            db.registerStudentForCourse(r,type);
        }
    }

    private int checkVacancyForReg(Registration r) {
        int cid = r.getCourse();
        int in = r.getIndex();
        Course c = CourseDB.getCourse(cid);
        CourseIndex index = null;
        if(r.getIndex() == c.getCourseCode())
        {
            //lec only
            index = c.getLecture();
            if(c.checkInVacancy(index)>0)
            return 1;
        }
        else {
            index = c.getIndexByIn(r.getIndex(),true);
            if(c.checkInVacancy(index)>0)
            {
                if(c.getLaboratoryIndex().size()==0)
                    return 2;
                else
                    return 3;
            }
        }
        return -1;
    }


}

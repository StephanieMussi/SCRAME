/*
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RegistrationMgr {

    RegistrationMgr() {
        db = new RegistrationDB();

    }


    RegistrationDB db;


    //Check for vacancy
    public int checkVacancy(int courseCode, int index) {
        int vacancy = 0;
        CourseIndex d;
        Course course = CourseDB.getCourse(courseCode);
        List b = course.getTutorialIndex();
        for (int y = 0; y < b.size(); y++) {
            d = (CourseIndex) b.get(y);
            if (d.getIndex() == index) {
                vacancy = d.getVacancy();
                if (vacancy > 0) {
                    d.setVacancy();
                    vacancy = d.getVacancy();
                }
            }

        }
        return vacancy;
    }

    //Register students to their selected course
    public int insertToRegistration(Registration registration) {
        int x = checkVacancy(registration.getCourse(), registration.getIndex());
        if (x != 0) {
            try {
                db.registerStudentForCourse(registration);
                System.out.println(registration.getStudentName() + ", Student ID: " + registration.getStudent() + " successfully registered for " + registration.getCourse() + ", Class Index: " + registration.getIndex() + ", Vacancy: " + x);
                throw new IOException();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 0;
        }
        //decrease course vacancy
        //ic = new IndexCourse(registration.getIndex()); //need a controller for indexcourse
        //ic.setVacancy(); //setVacancy() to decrement vacancy, should be in controller, not in entity


        else {
            System.out.println("Course: " + registration.getCourse() + ", Index: " + registration.getIndex() + " is full.");
            return 1;
        }
    }

    //Print student name in the class
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


    public void printByS(int studentId) {
        db.printByS(studentId);
    }


    public void registrationMenu() {
        Scanner sc = new Scanner(System.in);
        // TODO Auto-generated method stub
        int choice = 0;
        int index;
        while (choice < 3) {
            System.out.print("Choose:");
            System.out.print("\n1. Register student");
            System.out.print("\n2. Print registration list");
            System.out.println("\n3. Quit");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter integers only");
                sc.nextLine();
                int coursecode = 0;
                switch (choice) {
                    case 1:
                        int x = 0, y = 1, z = 0;
                        System.out.println("\n1. Enter Student ID: ");
                        int studentid = sc.nextInt();
                        while (x < 2) {

                            switch (y) {
                                case 1:
                                    System.out.println("\n1. Enter Course Code: ");
                                    sc.nextLine();//buffer
                                    coursecode = sc.nextInt(); //don't enter the break;
                                case 2:
                                    System.out.println("\n1. Enter Class Index: ");
                                    index = sc.nextInt();
                                    Registration reg = new Registration(coursecode, studentid, index, StudentDB.getSnamebySid(studentid));
                                    z = insertToRegistration(reg);
                                    break;
                                case 3:
                                    x = 3;
                            }

                            if (z == 0) {
                                System.out.println("\n1. Add another course? \n2. Quit");
                                x = sc.nextInt();
                                y = 1;
                            } else {
                                System.out.println("1. Choose another Course\n2. Choose another Index\n3. Quit");
                                y = sc.nextInt();
                            }
                        }
                        break;
                    case 2:
                        int a = 0;
                        while (a < 3) {
                            System.out.println("\n1. Choose by Class Index\n2. Choose by Student\n3.Quit");
                            a = sc.nextInt();
                            switch (a) {
                                case 1:
                                    System.out.println("\n1. Enter Class Index: ");
                                    index = sc.nextInt();
                                    db.printRegByIndex(index);
                                    break;
                                case 2:
                                    System.out.println("\n1. Enter Student ID: ");
                                    index = sc.nextInt();
                                    db.printByS(index);
                                    break;
                                default:
                                    break;
                            }

                        }

                        break;
                    default:
                        break;
                }
            }
        }
    }
}*/





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


    //Check for vacancy
    public int checkVacancy(int courseCode, int index) {
        int vacancy = 0;
        CourseIndex d;
        Course course = CourseDB.getCourse(courseCode);
        List b = course.getTutorialIndex();
        for (int y = 0; y < b.size(); y++) {
            d = (CourseIndex) b.get(y);
            if (d.getIndex() == index) {
                vacancy = d.getVacancy();
                if (vacancy > 0) {
                    d.setVacancy();
                    vacancy = d.getVacancy();
                }
            }

        }
        return vacancy;
    }

    //Register students to their selected course
    public int insertToRegistration(Registration registration) {
        int x = checkVacancy(registration.getCourse(), registration.getIndex());
        if (x != 0) {
            try {
                db.registerStudentForCourse(registration);
                System.out.println(" Student ID: " + registration.getStudent() + " successfully registered for " + registration.getCourse() + ", Class Index: " + registration.getIndex() + ", Vacancy: " + x);
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
        //decrease course vacancy
        //ic = new IndexCourse(registration.getIndex()); //need a controller for indexcourse
        //ic.setVacancy(); //setVacancy() to decrement vacancy, should be in controller, not in entity


        else {
            System.out.println("Course: " + registration.getCourse() + ", Index: " + registration.getIndex() + " is full.");
            return 1;
        }
    }

    //Print student name in the class
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

    private void printByC(int cid) {
        db.printByC(cid);
    }


    public void printByS(int studentId) {
        db.printByS(studentId);
    }


    public void registrationMenu() {
        int sel = 0;
        int index = -1;
        while (true) {
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

    private void registerS() {
        int sel = 0;
        int index = -1;
        int sid = -1;
        int cid = -1;
        do {
            System.out.println("enter student id:");
            sid = sc.nextInt();
        } while (StudentDB.getSbySid(sid) == null);
        do {
            System.out.println("enter course code:");
            cid = sc.nextInt();
        } while (CourseDB.getCourse(cid) == null);

        Course c = CourseDB.getCourse(cid);

        if (c.getTutorialIndex() == null && c.getLaboratoryIndex() == null)
        {
            regIntoDB(cid, sid);
        }
        else
        {
            do {
                System.out.println("enter index:");
                index = sc.nextInt();
            } while (c.getTutorialIndex()==null);
            regIntoDB(cid, sid, index);
        }

    }



    private void regIntoDB(int cid, int sid) {
        Registration r = new Registration(cid, sid, -1);
        db.registerStudentForCourse(r);
    }

    private void regIntoDB(int cid, int sid, int index) {
        Registration r = new Registration(cid, sid, index);
        db.registerStudentForCourse(r);
    }





    private void printReg() {
        int sel;
        int index;
        int cid;
        int sid;
        do {
            System.out.println("1. Choose by Class Index\n" +
                    "2. Choose by lecture\n" +
                    "3. Choose by Student\n" +
                    "4. keep on printing\n" +
                    "");
            System.out.println("enter your choice:");
            sel = sc.nextInt();
            switch (sel) {
                case 1:
                    System.out.println("Enter Class Index (print by lab/ tut): ");
                    index = sc.nextInt();
                    printRegByIndex(index);
                    break;
                case 2:
                    System.out.println("Enter course code (print by lec): ");
                    cid = sc.nextInt();
                    printByC(cid);
                    break;
                case 3:
                    System.out.println("Enter Student ID: ");
                    sid = sc.nextInt();
                    printByS(sid);
                    break;
                case 4:
                    System.out.println("keep on printing");
                    continue;
                default:
                    break;
            }
            }while (true) ;
    }
}
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrationMgr {


    RegistrationMgr() {
        db = new RegistrationDB();

    }


    Registration registration;
    RegistrationDB db;

    //Create registration object
    public void getInfo(int course, int student, int index) {
        String studentName = "";
        StudentInfo student2 = new StudentInfo();
        student2 = StudentDB.getSbySid(student);
        studentName = student2.getSname();
        registration = new Registration(course, student, index, studentName);
    }

    public void printAllReg()
    {
        db.printAllReg();
    }




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
    public int insertToRegistration() {
        int x = checkVacancy(registration.getCourse(), registration.getIndex());
        if (x != 0) {
            db = new RegistrationDB();
            try {
                db.registerStudentForCourse(registration);
                System.out.println(registration.getStudentName() + ", Student ID: " + registration.getStudent() + " successfully registered for " + registration.getCourse() + ", Class Index: " + registration.getIndex() + ", Vacancy: " + x);
                throw new IOException(  );
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
            choice = sc.nextInt();
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
                                db.printRegByIndex(index);
                            case 3:
                                x = 3;
                        }

                        if (z == 0) {
                            System.out.println("\n1. Add another course? \n2. Quit");
                            x = sc.nextInt();
                            y = 1;
                        } else
                        {
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


        /*public Course Test() {
        Course course = new Course();
        course.setCourseAU(1);
        course.setProfessorId(1);
        course.setCourseCode(2004);
        course.setCourseName("TAN KHENG WEE");
        course.setMaxCapacity(100);

        CourseIndex ci = new CourseIndex(1001, 100);
        CourseIndex ci2 = new CourseIndex(1002, 100);
        CourseIndex ci3 = new CourseIndex(1003, 100);
        ArrayList list = new <CourseIndex>ArrayList();
        list.add(ci);
        list.add(ci2);
        list.add(ci3);
        course.setLecture(ci);
        course.setTutorialIndexes(list);
        course.setLaboratoryIndexes(list);
        ArrayList list2 = new ArrayList<StudentInfo>();
        // TODO Auto-generated method stub
        StudentInfo s1 = new StudentInfo(0001, "ZOE");
        StudentInfo s2 = new StudentInfo(0002, "ZOZO");
        StudentInfo s3 = new StudentInfo(0003, "BOBO");
        list2.add(s1);
        list2.add(s2);
        list2.add(s3);
        course.setRegisteredStudents(list2);
        return course;
    }*/


}
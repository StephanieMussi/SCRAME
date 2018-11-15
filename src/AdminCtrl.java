/**
 * boundary class
 */

import java.util.Scanner;

public class AdminCtrl {
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);

        ProfessorMgr pMgr = new ProfessorMgr(sc, new SerializeDB( "prof" ));
        CourseMgr cMgr = new CourseMgr(sc, new SerializeDB( "course" ));
        StudentMgr sMgr = new StudentMgr(sc, new SerializeDB( "student" ));
        RegistrationMgr rMgr = new RegistrationMgr(sc, new SerializeDB( "registration" ));
        MarkRecordMgr mMgr = new MarkRecordMgr(sc, new SerializeDB( "markRecords" ));



        int sel;
        do {
            System.out.println("|---------------------- Welcome to SCRAME ----------------------|\n" +
                    "1 --- Add student to StudentDB\n" +
                    "2 --- Add course to CourseDB, including set assessment weightage\n\n" +
                    "3 --- Enter registration menu, including: \na)Register a student to a course \nb)print students by lecture/tut/lab\nc)print all courses registered by a student\n\n" +
                    "4 --- Enter mark, including coursework and exam mark\n" +
                    "5 --- Print course statistics\n" +
                    "6 --- Print student transcript\n" +
                    "7 --- Print all students in DB\n" +
                    "8 --- Print all courses in DB\n" +
                    "9 --- Print all registration in DB\n" +
                    "10 --- Print index by a course, check vacancy \n" +
                    "0 --- Exit program...");

            System.out.println("Please enter your choice:");
            sel = sc.nextInt();
            switch (sel) {
                case 1://1. add student
                    sMgr.addStudent();
                    System.out.println("The current student list is:");
                    sMgr.printAll();
                    continue;
                case 2://2. add course //6. set assessment weightage
                    try {
                        cMgr.addCourse();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("The current course list is:");
                    cMgr.printCourseList();
                    continue;
                case 3://3. register students to course //4. check vacancy can be implemented here
                    rMgr.registrationMenu();
                    continue;
                case 4://7. enter coursework mark //8. enter exam mark;
                    mMgr.assignMarks();
                    continue;
                case 5://9. print course statistics
                    mMgr.printCourseStat();
                    continue;
                case 6://10. print student transcript
                    mMgr.printStudentTranscript();
                    continue;
                case 7:
                    sMgr.printAll();
                    continue;
                case 8:
                    cMgr.printCourseList();
                    continue;
                case 9:
                    rMgr.printAllReg();
                    continue;
                case 10:
                    cMgr.printIndex();
                    continue;
                case 0:
                    System.out.println("Program exit...");
                    break;
                default:
                    System.out.println("Wrong input selection, please enter again!");
            }
        }while(sel>0 && sel<11);
        mMgr.markRecordDB.saveData();
        pMgr.saveData();
        sMgr.studentDB.saveData();
        cMgr.courseDB.saveData();
        rMgr.registrationDB.saveData();
    }

}
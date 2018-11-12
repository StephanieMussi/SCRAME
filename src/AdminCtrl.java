import java.util.Scanner;

public class AdminCtrl {
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        ProfessorMgr pMgr = new ProfessorMgr();
        CourseMgr cMgr = new CourseMgr();
        StudentMgr sMgr = new StudentMgr();
        RegistrationMgr rMgr = new RegistrationMgr();
        MarkRecordMgr mMgr = new MarkRecordMgr();



        int sel;
        do {
            System.out.println("|---------------------- Welcome to SCRAME ----------------------|\n" +
                    "1 --- Add student to StudentDB\n" +
                    "2 --- Add course to CourseDB, including set assessment weightage\n" +
                    "3 --- Enter registration menu, including print student list\n" +
                    "4 --- Enter mark, including coursework and exam mark\n" +
                    "5 --- Print course statistics\n" +
                    "6 --- Print student transcript\n" +
                    "7 --- Print all students in DB\n" +
                    "8 --- Print all courses in DB\n" +
                    "9 --- Print all registration in DB\n" +
                    "0 --- Exit program...");

            System.out.println("Please enter your choice:");
            sel = sc.nextInt();
            switch (sel) {
                case 1://1. add student
                    sMgr.addStudent();
                    continue;
                case 2://2. add course //6. set assessment weightage
                    cMgr.addCourse();
                    continue;
                case 3://3. register students to course //4. check vacancy can be implemented here
                    try {
                        rMgr.registrationMenu();
                    }
                    catch(isRegisterationException e)
                    {

                    }
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
                case 0:
                    System.out.println("Program exit...");
                    break;
                default:
                    System.out.println("Wrong input selection, please enter again!");
            }
        }while(sel>=0 && sel<10);


    }


}
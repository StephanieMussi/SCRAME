import java.util.Scanner;

public class AdminCtrl {
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        MarkRecordMgr mMgr = new MarkRecordMgr();
        StudentMgr sMgr = new StudentMgr();
        ProfessorMgr pMgr = new ProfessorMgr();
        RegistrationMgr rMgr = new RegistrationMgr();
        CourseMgr cMgr = new CourseMgr();



        int sel;
        do {
            System.out.println("Menu:\n" +
                    "1 --- add student to StudentDB\n" +
                    "2 --- add course to CourseDB, including set assessment weightage\n" +
                    "3 --- enter registration menu, including print student list\n" +
                    "4 --- enter mark, including coursework and exam mark\n" +
                    "5 --- print course statistics\n" +
                    "6 --- print student transcript\n" +
                    "7 --- print all students in DB\n" +
                    "8 --- print all courses in DB\n" +
                    "0 --- exit program...");

            System.out.println("please enter your choice:");
            sel = sc.nextInt();
            switch (sel) {
                case 1://1. add student
                    sMgr.addStudent();
                    continue;
                case 2://2. add course //6. set assessment weightage
                    cMgr.addCourse();
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
                case 0:
                    System.out.println("program exit...");
                    break;
                default:
                    System.out.println("wrong input selection, please enter again!");
            }
        }while(sel>=0 && sel<10);


    }


}

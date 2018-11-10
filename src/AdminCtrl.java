import java.io.*;
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


        int sel = sc.nextInt();
        switch(sel)
        {
            case 1://1. add student
                sMgr.addStudent();
                break;
            case 2://2. add course //6. set assessment weightage
                cMgr.addCourse();
                break;
            case 3://3. register students to course //4. check vacancy can be implemented here
                rMgr.registrationMenu();
                break;
            case 4://5. print student by lec/ tut/ lab //here print() assume all index are unique
                rMgr.print(0);
                break;
            case 5://7. enter coursework mark //8. enter exam mark;
                mMgr.assignMarks();
                break;
            case 6://9. print course statistics
                mMgr.printCourseStat();
                break;
            case 7://10. print student transcript
                mMgr.printStudentTranscript();
                break;
            default:
                break;
        }



    }


}

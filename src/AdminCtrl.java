import java.io.*;
import java.util.Scanner;

public class AdminCtrl {
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);



        //load and add students
        System.out.println("load students from data file");
        StudentMgr sMgr = new StudentMgr();
        System.out.println("how many students do you want to add");
        int noOfStu = sc.nextInt();
        System.out.println("please enter following information to add a student");
        for(int i= 0; i<noOfStu; i++)
        {
            sMgr.addStudent();
        }


        //load professors
        ProfessorMgr pMgr = new ProfessorMgr();
        System.out.println("do you want to print out prof list? 0 - ignore, 1 - print");
        int sel = sc.nextInt();
        if(sel ==1)
            pMgr.printAll();


        //load and add courses
        //define all attributes related to courses
        System.out.println("load courses from data file...");
        CourseMgr cMgr = new CourseMgr();
        cMgr.addCourse();


        //register students to courses





        //Register students
        RegistrationMgr rMgr = new RegistrationMgr();
        rMgr.registrationMenu();


        //assign marks
        MarkRecordMgr mMgr = new MarkRecordMgr();
        mMgr.assignMarks();


        //printout student script




    }


}

import java.util.*;
public class MarkRecordMgr {
    Scanner scan = new Scanner(System.in);
    private MarkRecordDB markRecordDB = new MarkRecordDB();

    public void assignMarks(){
        RegistrationDB registrationDB = new RegistrationDB();
        Registration registration;
        MarkRecord thisRecord;

        System.out.println("Please enter the student ID:");
        int sid = scan.nextInt();
        System.out.println("Please enter the course code:");
        int courseCode = scan.nextInt();
        thisRecord = markRecordDB.getRecord(sid,courseCode);
        System.out.println("choose: 1: enter exam mark 2:enter coursework marks");
        int ch = scan.nextInt();

        if(ch == 1){
            System.out.println("Enter exam mark(100 marks based):");
            double examMark = scan.nextInt();
            thisRecord.setMarkExam(examMark);
        }
        else if(ch == 2){
            double [] marksCA = thisRecord.getMarksCA();
            System.out.println("Mark for which coursework?(0-"+ (marksCA.length-1));
            int index = scan.nextInt();
            System.out.println("Enter coursework mark(100 marks based):");
            double courseMark = scan.nextDouble();
            thisRecord.setMarksCA(courseMark,index);
        }

    }

    public void printStudentMarks(int sid){

    }
}

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

    public void printStudentTranscript(){
        System.out.println("Please enter the student ID:");
        int sid = scan.nextInt();
        // get list of markrecords for the student
        ArrayList<MarkRecord> records = markRecordDB.getRecordListByStudent(sid);
        StudentInfo student = records.get(0).getRegistration().getStudent();
        System.out.println("Student name: " + student.getSname());
        for(int i = 0; i<records.size(); i++){

            MarkRecord thisRecord = records.get(i);
            Course iCourse = thisRecord.getRegistration().getCourse();
            double examGrades = thisRecord.getMarkExam();
            Assessment examWeightage = iCourse.getCourseWeightage().getExamination();
            double [] courseWorkGrades = thisRecord.getMarksCA();
            ArrayList<Assessment> courseWorkWeightage = iCourse.getCourseWeightage().getCourseWork();
            double mark = 0;

            System.out.println("Course info: "+iCourse.getCourseCode()+ " " + iCourse.getCourseName());
            System.out.println("Exam mark: " + examGrades);
            System.out.println("Exam weightage: " + examWeightage.getTotalWeightage());
            mark += examGrades*examWeightage.getTotalWeightage();

            for(int j=0; j<courseWorkWeightage.size(); j++){
                double cw = courseWorkWeightage.get(j).getTotalWeightage();
                System.out.println("Coursework "+j+" description: "+courseWorkWeightage.get(j).getType());
                System.out.println("Coursework  "+j+" mark: " + courseWorkGrades[j]);
                System.out.println("Coursework "+j+" weightage: " + cw);
                mark += courseWorkGrades[j]*cw;
            }
            System.out.println("Overall Grade for this course: "+ mark);
        }
    }
}

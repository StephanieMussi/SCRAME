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
        StudentInfo student = StudentDB.getSbySid(sid);
        System.out.println("Student name: " + student.getSname());
        for(int i = 0; i<records.size(); i++){

            MarkRecord thisRecord = records.get(i);
            int cid = thisRecord.getRegistration().getCourse();
            Course iCourse = CourseDB.getCourse(cid);
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


    public void printCourseStat()
    {
        System.out.println("Please enter the courseCode (int) :");
        int cid = scan.nextInt();
        Course c = CourseDB.getCourse(cid);
        CourseWeight weight = c.getCourseWeightage();
        //all records related to that course
        ArrayList<MarkRecord> records = markRecordDB.getRecordListByCourse(cid);

        System.out.println("Course info: "+c.getCourseCode()+ " " + c.getCourseName());

        //exam assessment
        Assessment exam = weight.getExamination();
        System.out.println("Exam weightage: " + exam.getTotalWeightage());
        //coursework assessment
        ArrayList<Assessment> ca = weight.getCourseWork();
        for (int i = 0; i< ca.size(); i++)
            System.out.println("Coursework [ "+ i+1 +" ] weightage: " + ca.get(i));

        System.out.println("following are the overall performance:");

        double sum = 0;
        int cot =0;
        for(int i= 0; i<records.size(); i++)
        {
            sum += records.get(i).getMarkExam();
            cot++;
        }
        System.out.println("Exam overall: " + sum/cot);

        for(int i = 0; i<ca.size(); i++)
        {
            double casum = 0;
            int cacot =0;
            for(int j =0; j<records.size();j++)
            {
                casum += records.get(j).getMarksCA()[j];
                cacot++;
            }
            System.out.println("Coursework [ "+ i+1 +" ] overall: " + casum / cacot);
        }

    }

}

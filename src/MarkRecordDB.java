import java.util.*;

public class MarkRecordDB {
    private ArrayList<MarkRecord> markRecords;

    public MarkRecordDB(){
        initialize();
    }

    public boolean checkCARecordExsits(int sid, int courseCode, int courseWorkNo){
        // check if the particular record exists, return true
        MarkRecord thisRecord = getRecord(sid, courseCode);
        double [] marksCA = thisRecord.getMarksCA();
    }

    // check for exam
    public boolean checkExamRecordExsits(MarkRecord thisRecord){
        // check if the particular record exists, return true

    }

    public MarkRecord getRecord(int sid, int courseCode){
        for(int i = 0; i<markRecords.size(); i++){
            MarkRecord thisRecord = markRecords.get(i);
            Registration reg = thisRecord.getRegistration();
            Course course = reg.getCourse();
            StudentInfo student = reg.getStudent();
            if(course.getCourseCode() == courseCode && student.getSid() == sid)
                return thisRecord;
        }
        return null;
    }

    public ArrayList<MarkRecord> getRecordListByStudent(int sid){
        ArrayList<MarkRecord> records = new ArrayList<>();
        for(int i = 0; i<markRecords.size(); i++){
            MarkRecord thisRecord = markRecords.get(i);
            Registration reg = thisRecord.getRegistration();
            Course course = reg.getCourse();
            StudentInfo student = reg.getStudent();
            if(student.getSid() == sid)
               records.add(thisRecord);
        }
        return records;
    }

    // for every registration in DB, create a MarkRecord
    private void initialize(){
        ArrayList<Registration> regList;
        MarkRecord thisRecord;
        RegistrationDB regDB = new RegistrationDB();
        regList = regDB.returnStudentList();
        for(int i = 0; i<regList.size(); i++){
            thisRecord = new MarkRecord(regList.get(i));
            markRecords.add(thisRecord);
        }
    }

}

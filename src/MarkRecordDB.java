import java.util.*;

public class MarkRecordDB {
    private static ArrayList<MarkRecord> markRecords;

    public  MarkRecordDB(){
        markRecords = new ArrayList<MarkRecord>();
    }

    public void addRecord(MarkRecord record){
        markRecords.add(record);
    }

    public boolean checkCARecordExsits(int sid, int courseCode, int courseWorkNo){
        // check if the particular record exists, return true
        MarkRecord thisRecord = getRecord(sid, courseCode);
        double [] marksCA = thisRecord.getMarksCA();
        return true;
    }

    // check for exam
    public boolean checkExamRecordExsits(MarkRecord thisRecord){
        // check if the particular record exists, return true
        return true;
    }



    public MarkRecord getRecord(int sid, int courseCode){
        for(int i = 0; i<markRecords.size(); i++){
            MarkRecord thisRecord = markRecords.get(i);
            Registration reg = thisRecord.getRegistration();
            if(reg.getCourse()==courseCode && reg.getStudent()==sid)
                return thisRecord;
        }
        return null;
    }


    public ArrayList<MarkRecord> getRecordListByStudent(int sid) {
        ArrayList<MarkRecord> recordOneStudent = new ArrayList<MarkRecord>();
        Registration oneReg;
        for(int i = 0; i<markRecords.size(); i++)
        {
            oneReg=markRecords.get(i).getRegistration();
            if( oneReg.getStudent()==sid)
            {
                recordOneStudent.add(markRecords.get(i));
            }
        }
        return recordOneStudent;
    }

    public static ArrayList<MarkRecord> getRecordListByCourse(int courseCode) {
        ArrayList<MarkRecord> recordOneCourse = new ArrayList<MarkRecord>();
        Registration oneReg;
        for(int i = 0; i<markRecords.size(); i++)
        {
            oneReg=markRecords.get(i).getRegistration();
            if( oneReg.getCourse()==courseCode)
            {
                recordOneCourse.add(markRecords.get(i));
            }
        }
        return recordOneCourse;
    }
}

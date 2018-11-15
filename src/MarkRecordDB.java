/**
 * contain a list of MarkRecord
 * assigning marks for exams and cas
 */

import java.util.*;


public class MarkRecordDB {
    private static ArrayList<MarkRecord> markRecords = new ArrayList<MarkRecord>();
    private SerializeToDatabaseInterface serializeDb;

    public MarkRecordDB(SerializeToDatabaseInterface serializeDb) {
        this.serializeDb = serializeDb;
        //load markRecords
        ArrayList<MarkRecord> listRead = (ArrayList<MarkRecord>) serializeDb.readSerializedObject();
        if (listRead == null)
            initialize();
        else markRecords = listRead;
    }


    /**
     * initialize pre-entered data
     */
    private void initialize() {
        Registration r1 = new Registration( 0001, 0001, 1 );
        Registration r2 = new Registration( 0002, 0002, 1 );
        Registration r3 = new Registration( 0003, 0003, 1 );
        MarkRecord m1 = new MarkRecord( r1, CourseDB.getCourse( 001 ) );
        MarkRecord m2 = new MarkRecord( r2, CourseDB.getCourse( 002 ) );
        MarkRecord m3 = new MarkRecord( r3, CourseDB.getCourse( 003 ) );
        markRecords.add( m1 );
        markRecords.add( m2 );
        markRecords.add( m3 );
    }

    /**
     * save data after program terminates
     */
    void saveData() {
        this.serializeDb.writeSerializedObject( markRecords );
    }

    void addRecord(MarkRecord record) {
        markRecords.add( record );
    }

    public MarkRecord getRecord(int sid, int courseCode) {
        for (int i = 0; i < markRecords.size(); i++) {
            MarkRecord thisRecord = markRecords.get( i );
            Registration reg = thisRecord.getRegistration();
            if (reg.getCourse() == courseCode && reg.getStudent() == sid)
                return thisRecord;
        }
        return null;
    }


    public ArrayList<MarkRecord> getRecordListByStudent(int sid) {
        ArrayList<MarkRecord> recordOneStudent = new ArrayList<MarkRecord>();
        Registration oneReg;
        for (int i = 0; i < markRecords.size(); i++) {
            oneReg = markRecords.get( i ).getRegistration();
            if (oneReg.getStudent() == sid) {
                recordOneStudent.add( markRecords.get( i ) );
            }
        }
        return recordOneStudent;
    }

    public static ArrayList<MarkRecord> getRecordListByCourse(int courseCode) {
        ArrayList<MarkRecord> recordOneCourse = new ArrayList<MarkRecord>();
        Registration oneReg;
        for (int i = 0; i < markRecords.size(); i++) {
            oneReg = markRecords.get( i ).getRegistration();
            if (oneReg.getCourse() == courseCode) {
                recordOneCourse.add( markRecords.get( i ) );
            }
        }
        return recordOneCourse;
    }
}

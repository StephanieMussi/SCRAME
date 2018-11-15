/**
 * contain a list of StudentInfo
 * loading and adding students info between programs and file
 * don't contain other reference
 */

import java.util.ArrayList;


public class StudentDB {

    private static ArrayList<StudentInfo> studentList = new ArrayList<StudentInfo>();
    private SerializeToDatabaseInterface serializeDb;

    /**
     * load student.dat
     * @param serializeDb
     */
    public StudentDB(SerializeToDatabaseInterface serializeDb) {
        this.serializeDb = serializeDb;
        //load Student Records
        ArrayList<StudentInfo> listRead = (ArrayList<StudentInfo>) serializeDb.readSerializedObject();
        if (listRead == null)
            initialize();
        else studentList = listRead;
    }


    /**
     * initialize pre-entered data when instantiate an object
     */
    private void initialize() {
        StudentInfo s1 = new StudentInfo( 0001, "Lin" );
        StudentInfo s2 = new StudentInfo( 0002, "Bella" );
        StudentInfo s3 = new StudentInfo( 0003, "Mike" );
        studentList.add( s1 );
        studentList.add( s2 );
        studentList.add( s3 );
    }

    void saveData() {
        this.serializeDb.writeSerializedObject( studentList );
    }


    /**
     * getter
     * @return StudentInfo
     * @param name
     * can also check for double entering
     */
    public static StudentInfo getSbySname(String name) {
        StudentInfo sinfo = null;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get( i ) == null)
                break;
            if (studentList.get( i ).getSname().compareTo( name ) == 0)
                sinfo = studentList.get( i );
        }
        return sinfo;
    }


    /**
     * getter
     * @param sid
     * @return sname
     */
    public static String getSnamebySid(int sid) {
        String sname = null;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get( i ) == null)
                break;
            if (studentList.get( i ).getSid() == sid)
                sname = studentList.get( i ).getSname();
        }
        return sname;
    }


    /**
     * getter
     * @return StudentInfo
     * @param sid
     */
    public static StudentInfo getSbySid(int sid) {
        StudentInfo sinfo = null;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get( i ) == null)
                break;
            if (studentList.get( i ).getSid() == sid)
                sinfo = studentList.get( i );
        }
        return sinfo;
    }


    /**
     * add student to StudentDB
     * @param sid
     * @param sname
     */
    public static void addStudent(int sid, String sname) {
        StudentInfo newStudent = new StudentInfo( sid, sname );
        studentList.add( newStudent );
    }


    /**
     * print all student info in student.dat
     */
    public void printAll() {
        for (int i = 0; i < studentList.size(); i++) {
            //hereby i is object index, not sid
            if (studentList.get( i ) == null)
                break;
            else
                System.out.println( studentList.get( i ).getSid() + "\t" + studentList.get( i ).getSname() );
        }
    }


}

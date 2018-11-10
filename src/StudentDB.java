import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    /*
     * contain a list of StudentInfo
     * loading and adding students info between programs and file
     * don't contain other reference
     */
    private static List<StudentInfo> studentList = new ArrayList<StudentInfo>();
    SerializeDB serialize;


    //load student.dat
    public StudentDB() {
        List<StudentInfo> list = new ArrayList<StudentInfo>();
        StudentInfo s1 = new StudentInfo( 0001, "Lin" );
        StudentInfo s2 = new StudentInfo( 0002, "Bella" );
        StudentInfo s3 = new StudentInfo( 0003, "Mike" );
        list.add( s1 );
        list.add( s2 );
        list.add( s3 );
        SerializeDB.writeSerializedObject( "student.dat", list );
        studentList = SerializeDB.readSerializedObject( "student.dat" );
    }


    /*
     * sub functionality
     * @return sid
     * @param name
     */
    public static int getSidbySname(String name) {
        int sid = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get( i ) == null)
                break;
            if (studentList.get( i ).getSname().compareTo( name ) == 0)
                sid = studentList.get( i ).getSid();
        }
        return sid;
    }


    /*
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


    /*
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


    /*
     * add student
     * using List method add()
     * required functionality
     */
    public static void addStudent(int sid, String sname) {
        StudentInfo newStudent = new StudentInfo( sid, sname );
        studentList.add( newStudent );
    }


    /*
     * method for testing
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

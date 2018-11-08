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
        studentList = SerializeDB.readSerializedObject( "student.dat" );
    }


    /*
     * sub functionality
     * @return sid
     * @param name
     */
    public int findSidbySname(String name) {
        // TODO Auto-generated method stub
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
    public StudentInfo findSbySname(String name) {
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
    public StudentInfo findSbySid(int sid) {
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
        // TODO Auto-generated method stub
        for (int i = 0; i < studentList.size(); i++) {
            //hereby i is object index, not sid
            if (studentList.get( i ) == null)
                break;
            else
                System.out.println( studentList.get( i ).getSid() + "\t" + studentList.get( i ).getSname() );
        }
    }


}

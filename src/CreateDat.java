import java.util.ArrayList;
import java.util.List;

/*
 * used for creating initial data
 * for student.dat
 * if you want to restart everything
 * delete student.dat in src folder
 * modify this code to what u want
 */
public class CreateDat {

    public static void main(String[] args) {
        List<StudentInfo> list = new ArrayList<StudentInfo>();
        // TODO Auto-generated method stub
        StudentInfo s1 = new StudentInfo( 0001, "ZOE" );
        StudentInfo s2 = new StudentInfo( 0002, "ZOZO" );
        StudentInfo s3 = new StudentInfo( 0003, "BOBO" );
        list.add( s1 );
        list.add( s2 );
        list.add( s3 );
        SerializeDB.writeSerializedObject( "student.txt", list );

    }

}

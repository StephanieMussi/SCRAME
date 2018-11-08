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
        //StudentInfo s2 = new StudentInfo( 0002, "ZOZO" );
        //StudentInfo s3 = new StudentInfo( 0003, "BOBO" );
        list.add( s1 );
        //list.add( s2 );
        //list.add( s3 );
        SerializeDB.writeSerializedObject( "student.dat", list );

        List<Professor> listp = new ArrayList<Professor> ();
        Professor p1 = new Professor( 001,"Tan wei", "Twei.ntu.edu.com", 91330111 );
        Professor p2 = new Professor( 002,"Chen mi", "Cmi.ntu.edu.com", 91330222 );
        Professor p3 = new Professor( 003,"Christ lin", "CHli.ntu.edu.com", 91330333);
        listp.add( p1 );
        listp.add( p2 );
        listp.add( p3 );
        SerializeDB.writeSerializedObject( "prof.dat", listp );



    }

}

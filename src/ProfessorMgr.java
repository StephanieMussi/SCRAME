import java.util.ArrayList;
import java.util.List;

public class ProfessorMgr {
    /*
    mix of professorDB and ProfMgr
     */
    private static ArrayList<Professor> profList = new ArrayList<Professor>();

    ProfessorMgr()
    {
        //load prof
        profList = SerializeDB.readSerializedObject("prof.dat");
        if(profList == null)
            initialize();
    }

    private void initialize(){
        Professor p1 = new Professor( 001,"Tan wei", "Twei.ntu.edu.com", 91330111 );
        Professor p2 = new Professor( 002,"Chen mi", "Cmi.ntu.edu.com", 91330222 );
        Professor p3 = new Professor( 003,"Christ lin", "CHli.ntu.edu.com", 91330333);
        profList.add( p1 );
        profList.add( p2 );
        profList.add( p3 );
        SerializeDB.writeSerializedObject( "prof.dat", profList );
    }





    public Professor findProfByPid(int id) {
        Professor prof = null;
         for (int i = 0; i < profList.size(); i++) {
            if (profList.get( i ) == null)
                break;
            if (profList.get( i ).getPid() == id)
                prof = profList.get(i);
        }
        return prof;
    }


    public static void printAll() {
        for (int i = 0; i < profList.size(); i++) {
            if (profList.get( i ) == null)
                break;
            else System.out.println(profList.get(i).getPid()+"\tname: "+profList.get(i).getName());
        }
    }


    public static void main(String [] args)
    {
        ProfessorMgr pMgr = new ProfessorMgr();
        ProfessorMgr.printAll();

    }
}

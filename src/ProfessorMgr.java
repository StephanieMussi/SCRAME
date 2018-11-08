import java.util.ArrayList;
import java.util.List;

public class ProfessorMgr {
    /*
    mix of professorDB and ProfMgr
     */
    private static List<Professor> profList = new ArrayList<Professor>();

    ProfessorMgr()
    {
        //load prof
        profList = SerializeDB.readSerializedObject( "prof.dat" );
    }


    public boolean findProfbyPname(String name) {
        boolean exist = false;
         for (int i = 0; i < profList.size(); i++) {
            if (profList.get( i ) == null)
                break;
            if (profList.get( i ).getName().compareTo( name ) == 0)
                exist = true;
        }
        return exist;
    }


    public static void printAll() {
        for (int i = 0; i < profList.size(); i++) {
            if (profList.get( i ) == null)
                break;
            else System.out.println(profList.get(i).getName());
        }
    }


    public static void main(String [] args)
    {
        ProfessorMgr pMgr = new ProfessorMgr();
        ProfessorMgr.printAll();

    }
}

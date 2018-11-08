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

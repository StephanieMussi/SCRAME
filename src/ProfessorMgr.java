import java.util.ArrayList;

public class ProfessorMgr {
    /*
    mix of professorDB and ProfMgr
     */
    private static ArrayList<Professor> profList = new ArrayList<Professor>();

    ProfessorMgr() {
        //load prof
        ArrayList<Professor> listRead = SerializeDB.readSerializedObject( "prof.dat" );
        if (listRead == null)
            initialize();
        else profList = listRead;
    }

    private void initialize() {
        Professor p1 = new Professor( 1, "Tan wei", "Twei.ntu.edu.com", 91330111 );
        Professor p2 = new Professor( 2, "Chen mi", "Cmi.ntu.edu.com", 91330222 );
        Professor p3 = new Professor( 3, "Christ lin", "CHli.ntu.edu.com", 91330333 );
        Professor p4 = new Professor( 4, "Low yen", "lowyen.ntu.edu.com", 91312312 );
        Professor p5 = new Professor( 5, "Teh Peng", "Tehpeng.ntu.edu.com", 91334534 );
        Professor p6 = new Professor( 6, "Leo Josh", "ljosh.ntu.edu.com", 91335533 );
        Professor p7 = new Professor( 7, "Teo Henry", "Thenry.ntu.edu.com", 91330099 );
        Professor p8 = new Professor( 8, "Yip Lionell", "yipl.ntu.edu.com", 91332321 );
        Professor p9 = new Professor( 9, "Boon Kiat", "boonk.ntu.edu.com", 91335562 );
        Professor p10 = new Professor( 10, "Chen Hwee", "chwee.ntu.edu.com", 91331347 );


        profList.add( p1 );
        profList.add( p2 );
        profList.add( p3 );
        profList.add( p4 );
        profList.add( p5 );
        profList.add( p6 );
        profList.add( p7 );
        profList.add( p8 );
        profList.add( p9 );
        profList.add( p10 );

    }


    public static void saveData() {
        SerializeDB.writeSerializedObject( "prof.dat", profList );
    }


    public static Professor findProfByPid(int id) {
        Professor prof = null;
        for (int i = 0; i < profList.size(); i++) {
            if (profList.get( i ) == null)
                break;
            if (profList.get( i ).getPid() == id)
                prof = profList.get( i );
        }
        return prof;
    }


    public static void printAll() {
        for (int i = 0; i < profList.size(); i++) {
            if (profList.get( i ) == null)
                break;
            else System.out.println( profList.get( i ).getPid() + "\tname: " + profList.get( i ).getName() );
        }
    }


    public static void main(String[] args) {
        ProfessorMgr pMgr = new ProfessorMgr();
        ProfessorMgr.printAll();

    }
}

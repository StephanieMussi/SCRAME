/**
 * include ProfessorDB as an attribute
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ProfessorMgr {

    private static ArrayList<Professor> profList = new ArrayList<Professor>();
    private SerializeToDatabaseInterface serializeDb;

    /**
     * constructor
     * @param sc
     * @param serializeDb
     */
    public ProfessorMgr(Scanner sc, SerializeToDatabaseInterface serializeDb) {
        this.serializeDb = serializeDb;
        //load profRecords
        ArrayList<Professor> listRead = (ArrayList<Professor>) serializeDb.readSerializedObject();
        if (listRead == null)
            initialize();
        else profList = listRead;
    }

    /**
     * initialize the pre-entered data
     */
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


    /**
     * save data after program terminates
     */
    void saveData() {
        this.serializeDb.writeSerializedObject( profList );
    }

    /**getter
     * @param pid
     * @return
     */
    static boolean isProfInDB(int pid) {
        for (int i = 0; i < profList.size(); i++) {
            if (profList.get( i ).getPid() == pid)
                return true;
        }
        return false;
    }

    /**
     * getter
     * @param id
     * @return
     */
    static Professor findProfByPid(int id) {
        Professor prof = null;
        for (int i = 0; i < profList.size(); i++) {
            if (profList.get( i ) == null)
                break;
            if (profList.get( i ).getPid() == id)
                prof = profList.get( i );
        }
        return prof;
    }

}

/**
 * used to store individual student info
 * if sid = -1, it's invalid
 */

import java.io.Serializable;
public class StudentInfo implements Serializable {

    int sid;
    String sname;

    public StudentInfo(int id, String name) {
        sid = id;
        sname = name;
    }


    public int getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }
}

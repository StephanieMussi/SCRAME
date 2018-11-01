import java.io.Serializable;

public class StudentInfo implements Serializable {
    /*
     * used to store individual student info
     * if sid = -1, it's invalid
     */
    int sid;
    String sname;

    public StudentInfo() {
        sid = -1;
        sname = "anonymous";
    }

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

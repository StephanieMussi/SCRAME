import java.io.Serializable;

public class Professor implements Serializable {
    private int pid;
    private String name;
    private String email;
    private int contact;

    public Professor(int id, String n, String e, int c) {
        pid = id;
        name = n;
        email = e;
        contact = c;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        if (o instanceof Professor) {
            Professor p = (Professor) o;
            return (getName().equals( p.getName() ));
        }
        return false;
    }

}


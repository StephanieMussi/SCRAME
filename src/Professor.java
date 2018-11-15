/**
 * contain basic prof info
 */

import java.io.Serializable;

public class Professor implements Serializable {
    private int pid;
    private String name;
    private String email;
    private int contact;

    /**
     * constructor
     * @param id
     * @param n
     * @param e
     * @param c
     */
    public Professor(int id, String n, String e, int c) {
        pid = id;
        name = n;
        email = e;
        contact = c;
    }


    /**
     * @return pid
     */
    public int getPid() {
        return pid;
    }


    /**
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * @param o
     * @return boolean  true->equal false->not equal
     */
    public boolean equals(Object o) {
        if (o instanceof Professor) {
            Professor p = (Professor) o;
            return (getName().equals( p.getName() ));
        }
        return false;
    }

}


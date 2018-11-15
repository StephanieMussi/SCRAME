/**
 * manipulate all registration records
 */

import java.util.ArrayList;
public class RegistrationDB {
    private static ArrayList<Registration> registrations = new ArrayList<Registration>();
    private SerializeToDatabaseInterface serializeDb;

    /**
     * constructor
     * @param serializeDb
     */
    public RegistrationDB(SerializeToDatabaseInterface serializeDb) {
        this.serializeDb = serializeDb;
        //load markRecords
        ArrayList<Registration> listRead = (ArrayList<Registration>) serializeDb.readSerializedObject();
        if (listRead == null)
            initialize();
        else registrations = listRead;
    }


    /**
     * initialize pre-entered data
     */
    private void initialize(){
        Registration r1 = new Registration(0001,0001,1001);
        Registration r2 = new Registration(0002,0002,2001);
        Registration r3 = new Registration(0003,0003,3001);
        Registration r4 = new Registration(0001,0002,1002);

        registrations.add(r1);
        registrations.add(r2);
        registrations.add(r3);
        registrations.add(r4);

    }

    /**
     * save data after program terminates
     */
    void saveData() {
        this.serializeDb.writeSerializedObject( registrations );
    }


    /**
     * register a valid student to a valid course
     * @param x
     * @param type
     */
    public void registerStudentForCourse(Registration x, int type) {
        //only have lec
        Course c = CourseDB.getCourse(x.getCourse());
        //update vacancy here
        if(type == 1)
        {
            c.getLecture().setVacancy();
        }
        else if(type == 2)
        {
            c.getLecture().setVacancy();
            c.getIndexByIn(x.getIndex(),true).setVacancy();
        }
        else if(type == 3)
        {
            //update lec vacancy
            c.getLecture().setVacancy();
            //update tut vacancy
            c.getIndexByIn(x.getIndex(), true).setVacancy();
            //update lab vacancy
            c.getIndexByIn(x.getIndex()).setVacancy();
        }
        System.out.println("Student ID:  " + x.getStudent() + "\tCourse Code:  " + x.getCourse() + "\tIndex:  "+ x.getIndex());
        System.out.println("Registered successfully!");
        registrations.add(x);
    }


    /**
     * getter
     * @param sid
     * @param courseCode
     * @return Registration
     */
    public static Registration getRegViaSidCourseCode(int sid, int courseCode){
        for(int i = 0; i< registrations.size(); i++){
            Registration thisReg = registrations.get(i);
            if(thisReg.getCourse() == courseCode && thisReg.getStudent() == sid)
                return thisReg;
        }
        return null;
    }


    /**
     * print all registration records
     */
    public void printAllReg() {

        for(int i = 0; i<registrations.size(); i++)
        {
            Registration r = registrations.get(i);
            System.out.println("Student ID:  " + r.getStudent() + "\tCourse Code:  " + r.getCourse()+ "\tIndex:  " + r.getIndex());
        }

    }


    /**
     * for displaying registration records by index
     * @param indexNum
     */
    public void printRegByIndex(int indexNum) {
        for(int i = 0; i<registrations.size(); i++)
        {
            Registration r = registrations.get(i);
            if(r.getIndex()==indexNum) {
                int sid = r.getStudent();
                int courseCode = r.getCourse();
                System.out.println("Student ID:  " + sid+"\tStudent Name:  " + StudentDB.getSnamebySid(sid) + "\tCourse Code:  " + courseCode+ "\tCourse Name:  "+CourseDB.getCourse(courseCode).getCourseName()+ "\tIndex:  " + r.getIndex());
            }
        }
    }


    /**
     * for displaying registration records by student id
     * @param studentId
     */
    public void printByS(int studentId) {
        for(int i = 0; i<registrations.size(); i++)
        {
            Registration r = registrations.get(i);
            if(r.getStudent()==studentId){
                int sid = r.getStudent();
                int courseCode = r.getCourse();
                System.out.println("Student ID:  " + sid+"\tStudent Name:  " + StudentDB.getSnamebySid(sid) + "\tCourse Code:  " + courseCode+ "\tCourse Name:  "+CourseDB.getCourse(courseCode).getCourseName()+ "\tIndex:  " + r.getIndex());
            }
        }
    }


    /**
     * for displaying registration records by course code
     * @param cid
     */
    public void printByC(int cid) {
        for(int i = 0; i<registrations.size(); i++)
        {
            Registration r = registrations.get(i);
            if(r.getCourse()==cid){
                int sid = r.getStudent();
                int courseCode = r.getCourse();
                System.out.println("Student ID:  " + sid+"\tStudent Name:  " + StudentDB.getSnamebySid(sid) + "\tCourse Code:  " + courseCode+ "\tCourse Name:  "+CourseDB.getCourse(courseCode).getCourseName()+ "\tIndex:  " + r.getIndex());
            }
        }
    }



    /**
     * check whether a record exist in RegistrationDB
     * @param r
     * @return boolean  true->exist  false->not exist
     */
    public boolean checkExist(Registration r) {
        for(int i = 0; i<registrations.size(); i++)
        {
            Registration reginDB = registrations.get(i);
            if(r.getStudent()==reginDB.getStudent() && r.getCourse()==reginDB.getCourse()) {
                System.out.println("Unable to register student to the same course again!");
                return true;
            }
            else if(r.getStudent()==reginDB.getStudent() && r.getIndex()==reginDB.getIndex())
            {
                System.out.println("Duplicate index!");
                return true;
            }
        }
        return false;
    }
}

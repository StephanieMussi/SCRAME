import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDB {
    private static ArrayList<Registration> registrations = new ArrayList<Registration>();

    public RegistrationDB() {
        //load registration
        ArrayList<Registration> listRead = SerializeDB.readSerializedObject("registrations.dat");
        if(listRead == null)
            initialize();
    }


    private void initialize(){
        Registration r1 = new Registration(0001,0001,1001);
        Registration r2 = new Registration(0002,0002,2001);
        Registration r3 = new Registration(0003,0003,3001);
        Registration r4 = new Registration(0001,0002,3001);

        registrations.add(r1);
        registrations.add(r2);
        registrations.add(r3);
        registrations.add(r4);
        SerializeDB.writeSerializedObject( "registration.dat", registrations );
    }




    public void registerStudentForCourse(Registration x) {
        System.out.println("student id: "+x.getStudent() +"course code:" +x.getCourse() + "index: "+ x.getIndex());
        System.out.println("register successfully!");
        registrations.add(x);
    }


    //get reg via sid and courseCode
    public static Registration getRegViaSidCourseCode(int sid, int courseCode){
        for(int i = 0; i< registrations.size(); i++){
            Registration thisReg = registrations.get(i);
            if(thisReg.getCourse() == courseCode && thisReg.getStudent() == sid)
                return thisReg;
        }
        return null;
    }



    //print student registration list
    public void printAllReg() {

        for(int i = 0; i<registrations.size(); i++)
        {
            Registration r = registrations.get(i);
            System.out.println("student id:  "+ r.getStudent()+ "\tcourseCode:  " + r.getCourse()+ "\tindex:  "+ r.getIndex());
        }

    }

    public void printRegByIndex(int indexNum) {
        for(int i = 0; i<registrations.size(); i++)
        {
            Registration r = registrations.get(i);
            if(r.getIndex()==indexNum)
                System.out.println("student id:  "+ r.getStudent()+ "\tcourseCode:  " + r.getCourse()+ "\tindex:  "+ r.getIndex());
        }
    }

    public void printByS(int studentId) {
        for(int i = 0; i<registrations.size(); i++)
        {
            Registration r = registrations.get(i);
            if(r.getStudent()==studentId)
                System.out.println("student id:  "+ r.getStudent()+ "\tcourseCode:  " + r.getCourse()+ "\tindex:  "+ r.getIndex());
        }
    }

    public void printByC(int cid) {
        for(int i = 0; i<registrations.size(); i++)
        {
            Registration r = registrations.get(i);
            if(r.getCourse()==cid)
                System.out.println("student id:  "+ r.getStudent()+ "\tcourseCode:  " + r.getCourse()+ "\tindex:  "+ r.getIndex());
        }
    }

    public boolean checkExist(Registration r) {
        for(int i = 0; i<registrations.size(); i++)
        {
            Registration reginDB = registrations.get(i);
            if(r.getStudent()==reginDB.getStudent() && r.getCourse()==reginDB.getCourse()) {
                System.out.println("cannot register one student to the same course again!");
                return true;
            }
            else if(r.getStudent()==reginDB.getStudent() && r.getIndex()==reginDB.getIndex())
            {
                System.out.println("duplicate index!");
                return true;
            }
        }
        return false;
    }
}


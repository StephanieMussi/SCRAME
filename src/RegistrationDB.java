import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDB implements Serializable {
    private static ArrayList<Registration> registrations = new ArrayList<Registration>();

    public RegistrationDB() {
        //load registration
        registrations = SerializeDB.readSerializedObject("registrations.dat");
        if(registrations == null)
            initialize();
    }

    private void initialize(){
        Registration r1 = new Registration(0001,0001,1,"Lin");
        Registration r2 = new Registration(0002,0002,1,"Bella");
        Registration r3 = new Registration(0003,0003,1,"Mike");
        registrations.add(r1);
        registrations.add(r2);
        registrations.add(r3);
        SerializeDB.writeSerializedObject( "registration.dat", registrations );
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

    //Insert entry to database

    /*public void registerStudentForCourse(Registration x) throws IOException {
        fileChecker();
        List list = new ArrayList();
        if (SerializeDB.readSerializedObject("registrations.dat") != null)
            list = (ArrayList) SerializeDB.readSerializedObject("registrations.dat");
        list.add(x);
        SerializeDB.writeSerializedObject("registrations.dat", list);
    }*/

    public void registerStudentForCourse(Registration x) {
        registrations.add(x);
    }


    //Return student list
    public ArrayList<Registration> returnStudentList() {

        try {
            ArrayList<Registration> list = (ArrayList) SerializeDB.readSerializedObject("registrations.dat");
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    //Function to check if registrations.txt is inside the folder if not will have ioException
    /*
    public static void fileChecker() throws IOException {
        int x;

        String fileName = "registrations.dat";
        File file = new File(fileName);
        if (!file.exists()) {
            CreateFile(fileName);
            System.out.println("Registration database does not exist. Creating one now....");
        }
    }

    //Create any file
    public static void CreateFile(String file) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("registrations.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    */
}

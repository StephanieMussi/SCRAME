import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RegistrationDB implements Serializable {
    private List<Registration> registration;

    public RegistrationDB() {
    }

    //Insert entry to database
    public void registerStudentForCourse(Registration x) throws IOException {
        fileChecker();
        List list = new ArrayList();
        if (SerializeDB.readSerializedObject("registration.dat") != null)
            list = (ArrayList) SerializeDB.readSerializedObject("registration.dat");
        list.add(x);
        SerializeDB.writeSerializedObject("registration.dat", list);
    }


    //Return student list
    public ArrayList<Registration> returnStudentList() {

        try {
            ArrayList<Registration> list = (ArrayList) SerializeDB.readSerializedObject("registration.dat");
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    //Function to check if registration.txt is inside the folder if not will have ioException
    public static void fileChecker() throws IOException {
        int x;

        String fileName = "registration.dat";
        File file = new File(fileName);
        if (!file.exists()) {
            CreateFile(fileName);
            System.out.println("Registration database does not exist. Creating one now....");
        }
    }

    //Create any file
    public static void CreateFile(String file) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("registration.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

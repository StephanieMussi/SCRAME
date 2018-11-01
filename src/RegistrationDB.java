import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RegistrationDB {
    private List<Registration> registration;

    public RegistrationDB() {
    }

    /*Used object as parameter.
    Insert entry to database */
    public void registerStudentForCourse(Registration x) {
        fileChecker();
        addRegistration(x);
    }

    /*This function should return a list of the registration
	Note: Will edit this part here accordingly to the database
	Prints Student ID, Course & Index*/
    public ArrayList<Registration> printStudentList(int indexNum) {

        ArrayList<Registration> x = new ArrayList<Registration>();
        try {
            x = readRegister("registration.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    /*
    public void setCourseworkMark(String courseCode, int studentID, int courseWorkNo, double courseworkMark){
        // iterate registration list, find that student courseCode pair
        registrations.get(i).setCourseworkMark(courseWorkNo, courseworkMark);
    }

    public void setExamMark(String courseCode, int studentID, double examMark){
        // iterate registration list, find that student courseCode pair
        registrations.get(i).setExamkMark(examMark);
    }

    public void printStudentTranscript(int studentID){
        //iterate reg list, for every registration.studentInfo.getID = studentID
        registrations.get(i);
    }

    public void saveRegistrationList(){
        SerializeDB.writeSerializedObject("registrations.dat",registrations);
    }
    */

    /*Should be in a separate class
    Function to check if registration.txt is inside the folder if not will have ioException.
    */
    public static void fileChecker() {
        int x;

        String fileName = "registration.txt";
        File file = new File(fileName); // it's not specifically a database. it's can be any random file.
        if (!file.exists()) {
            CreateFile(fileName);
            System.out.println("Registration database does not exist. Creating one now....");
        }
    }

    //Add in entry to database
    private static void addRegistration(Registration registration) {
        String SEPARATOR = "|";
        List alw = new ArrayList();
        ArrayList<Registration> x = new ArrayList<Registration>();
        try {

            x = readRegister("registration.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < x.size(); i++) {
            st = new StringBuilder();
            Registration y = (Registration) x.get(i);
            st.append(y.getCourse().trim());
            st.append(SEPARATOR);
            st.append(((Integer) y.getStudent()).toString());
            st.append(SEPARATOR);
            st.append(((Integer) y.getIndex()).toString());
            alw.add(st.toString());
        }
        st = new StringBuilder();
        st.append(registration.getCourse() + "|" + registration.getIndex() + "|" + registration.getStudent());
        // the text file should be formatted into "A|B|C"
        alw.add(st.toString());

        try {
            write("registration.txt", alw);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //Create any file.
    public static void CreateFile(String file) {
        try {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Update the file
    public static void write(String fileName, List data) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
    }

    //Read the file
    public static List read(String fileName) throws IOException {
        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }

    //Read the file. Separated the read() so the method can be reused.
    private static ArrayList<Registration> readRegister(String filename) throws IOException {

        ArrayList stringArray = (ArrayList) read(filename);
        ArrayList<Registration> readList = new ArrayList<Registration>();
        if (stringArray != null) {
            for (int i = 0; i < stringArray.size(); i++) {
                String st = (String) stringArray.get(i);
                StringTokenizer star = new StringTokenizer(st, "|");
                String coursecode = star.nextToken().trim();
                int student = Integer.parseInt(star.nextToken().trim());
                int index = Integer.parseInt(star.nextToken().trim());
                Registration x = new Registration(coursecode, student, index);
                readList.add(x);
            }
            return readList;
        } else
            return readList;
    }
}

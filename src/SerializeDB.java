import java.io.*;
import java.util.ArrayList;

// Note : When structure of the Object type (the class file) in the list changed
// the Serialized file may fail.
public class SerializeDB implements SerializeToDatabaseInterface
{
    private String fileName;


    public SerializeDB(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList readSerializedObject() {
        ArrayList pDetails = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(this.fileName);
            in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
            in.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // print out the size
        //System.out.println(" Details Size: " + pDetails.size());
        //System.out.println();
        return pDetails;
    }

    public void writeSerializedObject(ArrayList list) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(this.fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
            //	System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

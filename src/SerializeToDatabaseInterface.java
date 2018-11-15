/**
 * interface for manipulating serializable data
 */

import java.util.ArrayList;

public interface SerializeToDatabaseInterface {
    ArrayList<?> readSerializedObject();
    void writeSerializedObject(ArrayList list);
}

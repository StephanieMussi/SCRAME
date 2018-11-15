/**
 * Course Assessment class
 * Handles Course components (Course work and Exam)
 */

import java.io.Serializable;

public class Assessment implements Serializable {

    private String type;

    private double totalWeightage;

    /**
     * Constructor class
     * @param type
     * @param totalWeightage
     */
    public Assessment(String type, double totalWeightage) {
        this.type = type;
        this.totalWeightage = totalWeightage;
    }


    /**
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * @return double
     */
    public double getTotalWeightage() {
        return totalWeightage;
    }
}

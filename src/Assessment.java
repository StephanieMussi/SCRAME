import java.io.Serializable;

/***
 * Course Assessment class
 * Handles Course components (Course work and Exam)
 */
public class Assessment implements Serializable {
    /*
     * Type of Assessment (Exam or Coursework)
     */
    private String type;
    /*
     * Total Weightage of assessment
     */
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


    /*
     * Gets type of assessment
     */
    public String getType() {
        return type;
    }

    /*
     * Gets weightage of assessment
     */
    public double getTotalWeightage() {
        return totalWeightage;
    }
}

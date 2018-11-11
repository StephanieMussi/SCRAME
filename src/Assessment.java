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

    /***
     * Constructor class
     * @param type
     * @param totalWeightage
     */
    public Assessment(String type, double totalWeightage) {
        this.type = type;
        this.totalWeightage = 0; //Weightage is initialized to 0 first
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

    /*
     * Sets type of assessment (Exam or Assignment or Class participation etc)
     */
    public void setType(String type) {
        this.type = type;
    }

    /*
     * Sets weightage of type of assessment
     */
    public void setTotalWeightage(double totalWeightage) {
        if (totalWeightage < 0 || totalWeightage == 0) {
            totalWeightage = 0; // Min weightage of an assessment
        } else if (totalWeightage > 100) {
            totalWeightage = 100; // Max weightage of an assessment
        }
        this.totalWeightage = totalWeightage;
    }
}

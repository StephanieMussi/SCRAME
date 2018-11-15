/**
 * Course Database Class
 * Handles Course data of the Database
 */


import java.util.ArrayList;

public class CourseDB {

    private static ArrayList<Course> courseList = new ArrayList<Course>();
    private SerializeToDatabaseInterface serializeDb;

    /**
     * constructor
     * @param serializeDb
     */
    public CourseDB(SerializeToDatabaseInterface serializeDb) {
        this.serializeDb = serializeDb;
        //load markRecords
        ArrayList<Course> listRead = (ArrayList<Course>) serializeDb.readSerializedObject();
        if (listRead == null)
            initialize();
        else courseList = listRead;
    }




    /**
     * initialize pre-entered data
     */
    private void initialize() {
        Course s1 = new Course( 001, 001, "Engineers and Society", 3 );
        CourseWeight cw1 = new CourseWeight( new Assessment( "exam", 100 ), null );
        s1.addLecture( s1.getCourseCode(), 100 );
        int[] x = new int[]{20, 20, 20, 0};
        s1.addTutorial( x );
        s1.setCourseWeightage( cw1 );

        Course s2 = new Course( 002, 002, "Algorithms", 3 );
        CourseWeight cw2 = new CourseWeight( new Assessment( "exam", 100 ), null );
        s2.addLecture( s2.getCourseCode(), 100 );
        s2.addTutorial( x );
        s2.setCourseWeightage( cw2 );


        Course s3 = new Course( 003, 003, "Object Oriented Design and Programming", 3 );
        CourseWeight cw3 = new CourseWeight( new Assessment( "exam", 100 ), null );
        s3.addLecture( s3.getCourseCode(), 100 );
        s3.addTutorial( x );
        s3.setCourseWeightage( cw3 );


        courseList.add( s1 );
        courseList.add( s2 );
        courseList.add( s3 );

        this.saveData();


    }

    /**
     * save data after program terminates
     */
    void saveData() {
        this.serializeDb.writeSerializedObject( courseList );
    }

    /**
     * Parse a new "course" into the database
     */
    public void addCourse(Course newCourse) {
        courseList.add( newCourse );
    }


    /**
     * Getters for Course
     */
    public ArrayList<Course> getCourseList() {
        return this.courseList;
    }


    /**
     * getter
     * @param courseCode
     * @return
     */
    public static Course getCourse(int courseCode) {
        if (courseList.size() == 0) { //Return null if course list is empty
            return null;
        } else {
            for (int i = 0; i < courseList.size(); i++) {
                if (courseList.get( i ).getCourseCode() == courseCode) {
                    return courseList.get( i );
                }
            }
        }
        return null;
    }


    /**
     * print all index, including
     * vacancy / capacity
     * @param cid
     */
    public void printAllIndex(int cid) {
        Course c = getCourse( cid );
        if (c.getTutorialIndex() == null && c.getLaboratoryIndex() == null)
            System.out.println( "No tutorial and lab session for this course." );
        if (c.getTutorialIndex().size() > 0) {
            //System.out.print("Course Code"+ "\t\tVacancy" + "\n");
            System.out.printf( "%s\t\t%s\n", "Index", "Vacancy" );
            c.printIndex();
            System.out.println( "\n" );
        }
    }

}



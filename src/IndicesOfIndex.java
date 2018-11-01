/***
 * Enum class creation of the Indexes of a course
 */
public enum IndicesOfIndex {

    LECTURE {

        public String toString() {
            return "Lecture";
        }
    },
    TUTORIAL {

        public String toString() {
            return "Tutorial";
        }
    },
    LABORATORY {

        public String toString() {
            return "Lab";
        }
    };

    public static int getLessonsInIndex() {
        return 3;
    }

}

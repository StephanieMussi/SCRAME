/***
 * Enum class creation of the Indexes of a course
 */
public enum LessonType {

    lecture {

        public String toString() {
            return "Lecture";
        }
    },
    tutorial {

        public String toString() {
            return "Tutorial";
        }
    },
    lab {

        public String toString() {
            return "Laboratory";
        }
    };

    public static int getLessonsInIndex() {
        return 3;
    }

}
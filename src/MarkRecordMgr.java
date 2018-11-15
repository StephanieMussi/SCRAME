import java.util.*;

public class MarkRecordMgr {
    Scanner scan;
    private SerializeToDatabaseInterface serializeDb;
    public MarkRecordDB markRecordDB;

    public MarkRecordMgr(Scanner scan, SerializeToDatabaseInterface serializeDb) {
        this.scan = scan;
        this.serializeDb = serializeDb;
        this.markRecordDB = new MarkRecordDB( this.serializeDb );
    }

    /**
     *
     */
    public void assignMarks() {
        //Registration registration;
        int courseCode = -1, sid = -1;
        double examMark = -1, courseMark = -1;
        boolean success = false;
        MarkRecord thisRecord;
        Registration reg;
        int counter = 0;
        do {
            if (counter != 0)
                System.out.println( "Registration does not exist, please register first." );
            counter++;
            System.out.println( "Please enter the student ID:" );
            try {
                sid = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println( "Please enter integers only" );
                scan.nextLine();
            }
            System.out.println( "Please enter the course code:" );
            try {
                courseCode = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println( "Please enter integers only" );
                scan.nextLine();
            }
            thisRecord = markRecordDB.getRecord( sid, courseCode );
            reg = RegistrationDB.getRegViaSidCourseCode( sid, courseCode );
        } while (reg == null);
        // if the record haven't been initialized
        if (thisRecord == null) {
            thisRecord = new MarkRecord( reg, CourseDB.getCourse( courseCode ) );
            markRecordDB.addRecord( thisRecord );
        }
        boolean exit = false;
        do {
            System.out.println( "Choose:\n1: Enter exam mark\n2: Enter coursework marks" );
            int choice = -1;
            do {
                try {
                    choice = scan.nextInt();
                    if (choice < 1 || choice > 2) {
                        throw new isInvalidInputException( "Choice" );
                    }
                    success = true;
                } catch (isInvalidInputException e) {
                    System.out.println( "Please enter only '1' or '2'." );
                } catch (InputMismatchException e) {
                    System.out.println( "Please enter integers only." );
                    scan.nextLine();
                }
            } while (!success);
            success = false;
            if (choice == 1) {
                do {
                    try {
                        System.out.println( "Enter exam mark (100 marks based):" );
                        examMark = scan.nextInt();
                        if (examMark < 0 || examMark > 100) {
                            throw new isInvalidInputException( "Exam Marks" );
                        }
                    } catch (isInvalidInputException e) {
                        System.out.println( "Please enter values between 0 to 100" );
                        scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println( "Please enter a correct value." );
                        scan.nextLine();
                    }
                } while (examMark < 0 || examMark - 100 > 0.000001);
                thisRecord.setMarkExam( examMark );
            } else if (choice == 2) {
                double[] marksCA;
                try {
                    marksCA = thisRecord.getMarksCA();
                } catch (NullPointerException e) {
                    System.out.println( "This course does not have coursework!" );
                    continue;
                }

                int index = -1;
                do {
                    try {
                        System.out.println( "Mark for which coursework? ( 0-" + (marksCA.length - 1) + ")" );
                        index = scan.nextInt();
                        if (index < 0 || index >= marksCA.length)
                            throw new isRecordNotFoundException( "This ca" );
                    } catch (isRecordNotFoundException e) {
                        System.out.println( e.getMessage() );
                    }
                } while (index < 0 || index >= marksCA.length);


                do {
                    try {
                        System.out.println( "Enter coursework mark (100 marks based):" );
                        courseMark = scan.nextDouble();
                        if (courseMark < 0 || courseMark > 100) {
                            throw new isInvalidInputException( "Course Marks" );
                        }
                    } catch (isInvalidInputException e) {
                        System.out.println( "Please enter values between 0 to 100" );
                        scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println( "Please enter a correct value" );
                        scan.nextLine();
                    }
                } while (courseMark < 0 || courseMark - 100 > 0.000001);
                thisRecord.setMarksCA( courseMark, index );
            }

            int ch = -1;
            do {
                try {
                    System.out.println( "Do you want to continue setting marks for this student and course? (0 for no, 1 for yes):" );
                    ch = scan.nextInt();
                    if (!(ch == 0 || ch == 1))
                        throw new isInvalidInputException( "please enter 0 or 1 only" );
                    else if (ch == 0)
                        exit = true;
                } catch (isInvalidInputException e) {
                    System.out.println( "Invalid input, please enter again (0 or 1):" );
                }
                scan.nextLine();
            } while (!(ch == 0 || ch == 1));

        } while (!exit);

    }

    public void printStudentTranscript() {
        //valid student id
        int sid = -1;

        do {
            try {
                System.out.println( "Please enter the student ID:" );
                sid = scan.nextInt();
                if (StudentDB.getSbySid( sid ) == null)
                    throw new isRecordNotFoundException( "Student ID" );
            } catch (isRecordNotFoundException e) {
                System.out.println( e.getMessage() );
            } catch (InputMismatchException e) {
                System.out.println( "Please enter integer values only." );
                scan.nextLine();
            }
        } while (StudentDB.getSbySid( sid ) == null);

        // get list of markrecords for the student
        ArrayList<MarkRecord> records = markRecordDB.getRecordListByStudent( sid );
        StudentInfo student = StudentDB.getSbySid( sid );
        System.out.println( "Student name: " + student.getSname() );
        for (int i = 0; i < records.size(); i++) {

            MarkRecord thisRecord = records.get( i );
            int cid = thisRecord.getRegistration().getCourse();
            Course iCourse = CourseDB.getCourse( cid );
            double examGrades = thisRecord.getMarkExam();
            Assessment examWeightage = iCourse.getCourseWeightage().getExamination();
            double[] courseWorkGrades = thisRecord.getMarksCA();
            ArrayList<Assessment> courseWorkWeightage = iCourse.getCourseWeightage().getCourseWork();
            double mark = 0;

            System.out.println( "Course info: " + iCourse.getCourseCode() + " " + iCourse.getCourseName() );
            System.out.println( "Exam mark: " + examGrades );
            System.out.println( "Exam weightage: " + examWeightage.getTotalWeightage() + "%" );
            mark += examGrades * examWeightage.getTotalWeightage() / 100;

            if (courseWorkGrades != null) {
                for (int j = 0; j < courseWorkWeightage.size(); j++) {
                    double cw = courseWorkWeightage.get( j ).getTotalWeightage();
                    System.out.println( "Coursework " + j + " description: " + courseWorkWeightage.get( j ).getType() );
                    System.out.println( "Coursework  " + j + " mark: " + courseWorkGrades[j] );
                    System.out.println( "Coursework " + j + " weightage: " + cw + "%" );
                    mark += courseWorkGrades[j] * cw / 100;
                }
            }

            System.out.println( "Overall Grade for this course: " + mark );
        }
    }


    public void printCourseStat() {
        int cid = -1, counter = 0;
        Course c = null;
        boolean valid;
        do {
            valid = true;
            if (counter != 0)
                System.out.println( "Invalid coursecode! Please enter again." );
            counter++;
            System.out.println( "Please enter the course code (int):" );
            try {
                cid = scan.nextInt();
            } catch (InputMismatchException e) {
                valid = false;
                scan.nextLine();
            }
            c = CourseDB.getCourse( cid );
            if (c == null)
                valid = false;
        } while (!valid);
        CourseWeight weight = c.getCourseWeightage();
        //all records related to that course
        ArrayList<MarkRecord> records = markRecordDB.getRecordListByCourse( cid );

        System.out.println( "Course info: " + c.getCourseCode() + " " + c.getCourseName() );

        //exam assessment
        Assessment exam = weight.getExamination();
        System.out.println( "Exam weightage: " + exam.getTotalWeightage() + "%" );
        //coursework assessment
        ArrayList<Assessment> ca = weight.getCourseWork();
        if (ca != null) {
            for (int i = 0; i < ca.size(); i++)
                System.out.println( "Coursework [ " + (i + 1) + " ] weightage: " + ca.get( i ).getTotalWeightage() + "%" );
        }

        System.out.println( "Following are the overall performance:" );
        double sum = 0;
        int cot = 0;
        double total = 0;
        for (int i = 0; i < records.size(); i++) {
            sum += records.get( i ).getMarkExam();
            cot++;
        }
        System.out.println( "Exam overall: " + sum / cot + " marks" );
        total += sum / cot * exam.getTotalWeightage();

        if (ca != null) {
            for (int i = 0; i < ca.size(); i++) {
                double casum = 0;
                for (int j = 0; j < records.size(); j++) {
                    casum += records.get( j ).getMarksCA()[i];
                }
                System.out.println( "Coursework [ " + (i + 1) + " ] overall: " + casum / records.size() + " marks" );
                total += casum / records.size() * ca.get( i ).getTotalWeightage();
            }
        }
        System.out.println( "Exam and Coursework overall together: " + total / 100 );
    }
}
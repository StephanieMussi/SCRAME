import java.util.*;

public class MarkRecordMgr {
    Scanner scan = new Scanner( System.in );
    private MarkRecordDB markRecordDB = new MarkRecordDB();

    public void assignMarks() {
        //Registration registration;
        int courseCode = -1, sid = -1;
        double examMark = -1, courseMark = -1;
        boolean success = false;
        MarkRecord thisRecord;
        Registration reg;
        int counter = 0;
        do {
            if(counter!=0)
                System.out.println("Registration not exist, please register first.");
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
            thisRecord = new MarkRecord( reg );
            markRecordDB.addRecord( thisRecord );
        }
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
            System.out.println( "Enter exam mark (100 marks based):" );
            try {
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
            thisRecord.setMarkExam( examMark );
        } else if (choice == 2) {
            double[] marksCA = thisRecord.getMarksCA();
            System.out.println( "Mark for which coursework? ( 0-" + (marksCA.length - 1) );
            int index = scan.nextInt();
            System.out.println( "Enter coursework mark(100 marks based):" );
            try {
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
            thisRecord.setMarksCA( courseMark, index );
        }

    }

    public void printStudentTranscript() {
        System.out.println( "Please enter the student ID:" );
        int sid = scan.nextInt();
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
            System.out.println( "Exam weightage: " + examWeightage.getTotalWeightage() );
            mark += examGrades * examWeightage.getTotalWeightage();

            if(courseWorkGrades!=null)
            {
                for (int j = 0; j < courseWorkWeightage.size(); j++) {
                    double cw = courseWorkWeightage.get( j ).getTotalWeightage();
                    System.out.println( "Coursework " + j + " description: " + courseWorkWeightage.get( j ).getType() );
                    System.out.println( "Coursework  " + j + " mark: " + courseWorkGrades[j] );
                    System.out.println( "Coursework " + j + " weightage: " + cw );
                    mark += courseWorkGrades[j] * cw;
                }
            }

            System.out.println( "Overall Grade for this course: " + mark );
        }
    }


    public void printCourseStat() {
        int cid = -1, counter=0;
        Course c = null;
        boolean valid;
        do{
            valid = true;
            if(counter != 0)
                System.out.println("Invalid coursecode! Please enter again.");
            counter++;
            System.out.println( "Please enter the courseCode (int) :" );
            try{
                cid = scan.nextInt();
            }catch (InputMismatchException e){
                valid = false;
                scan.nextLine();
            }
            c = CourseDB.getCourse( cid );
            if(c == null)
                valid = false;
        }while (!valid);
        CourseWeight weight = c.getCourseWeightage();
        //all records related to that course
        ArrayList<MarkRecord> records = markRecordDB.getRecordListByCourse( cid );

        System.out.println( "Course info: " + c.getCourseCode() + " " + c.getCourseName() );

        //exam assessment
        Assessment exam = weight.getExamination();
        System.out.println( "Exam weightage: " + exam.getTotalWeightage() );
        //coursework assessment
        ArrayList<Assessment> ca = weight.getCourseWork();
        if(ca!= null) {
            for (int i = 0; i < ca.size(); i++)
                System.out.println("Coursework [ " + i + 1 + " ] weightage: " + ca.get(i).getTotalWeightage());
        }

        System.out.println( "following are the overall performance:" );
        double sum = 0;
        int cot = 0;
        for (int i = 0; i < records.size(); i++) {
            sum += records.get( i ).getMarkExam();
            cot++;
        }
        System.out.println( "Exam overall: " + sum / cot );

        if(ca!= null) {
            for (int i = 0; i < ca.size(); i++) {
                double casum = 0;
                for (int j = 0; j < records.size(); j++) {
                    casum += records.get(j).getMarksCA()[j];
                }
                System.out.println("Coursework [ " + i + 1 + " ] overall: " + casum / ca.size());
            }
        }

    }
}
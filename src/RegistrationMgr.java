import java.util.ArrayList;

public class RegistrationMgr {

    RegistrationMgr() {

    }

    Registration registration;
    RegistrationDB db;
    //IndexCourse ic;

    //Create registration object
    public void getinfo(String course, int student, int index) {
        registration = new Registration(course, student, index);
    }

    //Check for vacancy
    public int checkVacancy(String courseCode, int index) {
        int vacancy;
        //CourseMgr courseMgr = new CourseMgr();
        //vacancy = courseMgr.checkVacancyByCourseCode(courseCode, index); //Assuming it's working
        vacancy = 10; //Dummy value
        return vacancy;
    }

    //Register students to their selected course
    public void insertToRegistration() {
        if (checkVacancy(registration.getCourse(), registration.getIndex()) != 0) {
            db = new RegistrationDB();
            db.registerStudentForCourse(registration);
            //decrease course vacancy
            //ic = new IndexCourse(registration.getIndex()); //need a controller for indexcourse
            //ic.setVacancy(); //setVacancy() to decrement vacancy, should be in controller, not in entity

        } else {
            //return error (exception error, message error etc.)
        }
    }

    //Print database
    public void print() {
        Registration r;

        ArrayList<Registration> registrationList = new ArrayList<Registration>();
        db = new RegistrationDB();

        registrationList = db.printStudentList(1); //
        for (int z = 0; z < registrationList.size(); z++) {
            r = registrationList.get(z);
            System.out.println("Student ID: " + r.getStudent());
            System.out.println("Course: " + r.getCourse());
            System.out.println("Index: " + r.getIndex());
            System.out.println("---------------");
        }
    }
}

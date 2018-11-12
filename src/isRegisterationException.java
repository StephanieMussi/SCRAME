/***
 * Exception class to deal with invalid registration - Not found
 */
public class isRegisterationException extends Exception {
    public isRegisterationException(String object) {

        super(object + " not found!");
    }

}


/*
 * Exception class to deal with invalid registration - Not found
 */
/*public class isRegisterationException extends Exception {

    int x;
    public isRegisterationException(String object, int x) {

        //super(object + " not found!");
        switch(x)
        {
            case 0: System.out.println("Student is already registered in that course.");
                this.x=0;
                break;
            case 1: System.out.println("Course is not found.");
                this.x=1;
                break;
            case 2: this.x=1;
                break;
        }
    }

    public int getx()
    {
        return x;
    }
}*/
/**
 * Exception class dealing with invalid indexes
 */
public class isInvalidIndexException extends Exception{

        public isInvalidIndexException() {
            super("Input is invalid!!");
        }

        public isInvalidIndexException(String object) {

            super("Index is invalid. Please enter " + object);
        }

}

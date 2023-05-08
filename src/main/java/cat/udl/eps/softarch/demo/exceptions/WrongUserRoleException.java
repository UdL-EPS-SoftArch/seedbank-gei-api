package cat.udl.eps.softarch.demo.exceptions;

public class WrongUserRoleException extends RuntimeException {
    public WrongUserRoleException(String message) {
        super(message);
    }
}

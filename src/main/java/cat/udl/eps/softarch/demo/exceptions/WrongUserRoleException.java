package cat.udl.eps.softarch.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "El usuario debe ser de tipo propagator")
public class WrongUserRoleException extends RuntimeException {
    public WrongUserRoleException(String message) {
        super(message);
    }
}

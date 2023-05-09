package cat.udl.eps.softarch.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "El usuario debe ser de tipo propagator")
public class WrongUserRoleException extends RuntimeException { }

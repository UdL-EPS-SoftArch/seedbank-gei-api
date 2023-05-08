package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RepositoryEventHandler
public class RequestEventHandler {

    final Logger logger = LoggerFactory.getLogger(User.class);

    final UserRepository userRepository;

    final RequestRepository requestRepository;

    public RequestEventHandler(UserRepository userRepository, RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    @HandleBeforeCreate
    public boolean handleRequestPreCreate(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Request request) throws IOException {
        logger.info("Before creating: {}", request.toString());
        if (request.getPropagator() == null) {
            httpResponse.getWriter().write("El usuario debe ser de tipo propagator");
            httpResponse.setStatus(514);
            return false;
        }
        return true;
    }
}
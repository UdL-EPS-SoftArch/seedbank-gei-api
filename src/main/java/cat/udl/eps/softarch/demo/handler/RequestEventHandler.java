package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.exceptions.UserNotPropagator;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

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
    public void handleRequestPreCreate(Request request) {
        logger.info("Before creating: {}", request.toString());
        if (request.getPropagator() == null) throw new UserNotPropagator();
    }
}
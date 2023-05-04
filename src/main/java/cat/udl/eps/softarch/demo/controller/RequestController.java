package cat.udl.eps.softarch.demo.controller;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.mothers.RequestMother;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@BasePathAwareController
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @PostMapping("/requests")
    public @ResponseBody ResponseEntity<Request> createRequest(HttpServletRequest rawRequest) throws IOException {
        String requestBody = rawRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(requestBody);
        JsonNode propagatorNode = rootNode.get("propagator");
        Request request = mapper.readValue(requestBody, Request.class);
        Request savedRequest = requestRepository.save(request);
        return ResponseEntity.ok(savedRequest);
    }
}

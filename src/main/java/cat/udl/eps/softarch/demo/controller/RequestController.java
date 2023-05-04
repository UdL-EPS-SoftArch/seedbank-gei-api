package cat.udl.eps.softarch.demo.controller;

import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@BasePathAwareController
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @PostMapping("/requests")
    public @ResponseBody ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request newRequest = new Request();
        newRequest.setPropagator(request.getPropagator());
        newRequest.setDate(request.getDate());
        newRequest.setWeight(request.getWeight());
        newRequest.setAmount(request.getAmount());
        newRequest.setLocation(request.getLocation());
        Request savedRequest = requestRepository.save(newRequest);
        return ResponseEntity.ok(savedRequest);
    }
}

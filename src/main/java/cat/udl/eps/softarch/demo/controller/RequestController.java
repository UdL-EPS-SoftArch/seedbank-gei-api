package cat.udl.eps.softarch.demo.controller;

import cat.udl.eps.softarch.demo.domain.Request;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@BasePathAwareController
public class RequestController {

    @PostMapping("/requests")
    public @ResponseBody ResponseEntity<Request> createRequest() {
        return null;
    }
}

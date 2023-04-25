package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.mothers.RequestMother;
import cat.udl.eps.softarch.demo.mothers.TakeMother;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class RequestStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private TakeRepository takeRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PropagatorRepository propagatorRepository;

    private Take take;

    private Propagator propagator;

    private String previousLocation;

    private Request request;

    @And("User {string} is the propagator")
    public void userIsThePropagator(String user) {
        this.propagator = propagatorRepository.findById(user).orElseThrow();
    }

    @And("A valid take action exists for the propagator")
    public void aValidTakeActionExistsForThePropagator() {
        this.take = TakeMother.getValidTake();
        takeRepository.save(this.take);
    }

    @When("The propagator creates a request from the take action")
    public void thePropagatorCreatesARequestFromTheTakeAction() throws Exception {
        this.request = RequestMother.getValidRequestFor(propagator, take);
        stepDefs.result = stepDefs.mockMvc.perform(post("/requests")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                        .content(stepDefs.mapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There is {int} request created")
    public void thereIsRequestCreated(int numRequest) {
        assertEquals(numRequest, requestRepository.count());
    }

    @But("There is no Propagator")
    public void thereIsNoPropagator() {
        assertNull(propagator);
    }

    @But("There is no Take for the propagator")
    public void thereIsNoTakeForThePropagator() {
        assertEquals(0, takeRepository.count());
    }

    @When("The propagator removes the request")
    public void thePropagatorRemovesTheRequest() throws Exception {
        var request = requestRepository.findAll().iterator().next();
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/requests/{id}", request.getId())
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("The propagator updates the request")
    public void thePropagatorUpdatesTheRequest() throws Exception {
        var request = requestRepository.findAll().iterator().next();
        previousLocation = request.getLocation();
        stepDefs.result = stepDefs.mockMvc.perform(patch("/requests/{id}", request.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                        .content(new JSONObject().put("location", "new location").toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The new request is updated")
    public void theNewRequestIsUpdated() {
        var request = requestRepository.findAll().iterator().next();
        assertNotEquals(previousLocation, request.getLocation());
    }

    @When("The propagator updates the request with an empty body")
    public void thePropagatorUpdatesTheRequestWithAnEmptyBody() throws Exception {
        request = requestRepository.findAll().iterator().next();
        previousLocation = request.getLocation();
        stepDefs.result = stepDefs.mockMvc.perform(patch("/requests/{id}", request.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The new request is not updated")
    public void theNewRequestIsNotUpdated() {
        var expectedRequest = request;
        request = requestRepository.findAll().iterator().next();
        var actualRequest = request;
        assertEquals(expectedRequest, actualRequest);
    }

    @When("I retrieve all requests")
    public void iRetrieveAllRequests() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/requests")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The response contains {int} requests")
    public void theResponseContainsRequests(int numRequests) throws Exception {
        stepDefs.result.andExpect(jsonPath("$._embedded.requests", hasSize(numRequests)));
    }
}

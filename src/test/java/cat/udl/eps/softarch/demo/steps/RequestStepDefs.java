package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.mothers.TakeMother;
import cat.udl.eps.softarch.demo.mothers.RequestMother;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
}

package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CreateTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private TakeRepository takeRepository;

    @Given("^There is no Take available with id (\\d+)$")
    public void thereIsNoTakeAvailableWithId(Long id) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/takes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("^I create a new Take with id (\\d+)$")
    public void iCreateANewTake(Long id) throws Throwable {
        Take take = new Take();
        take.setTakeDate(ZonedDateTime.now());
        take.setId(id);
        take.setAmount(5);
        take.setWeight(new BigDecimal("5"));
        take.setLocation("Lleida");

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(take))
        );
    }

    @And("^Take has been created with id (\\d+)$")
    public void takeHasBeenCreated(Long id) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/takes/{id}", id)
                                .accept(MediaType.APPLICATION_JSON));
    }
}

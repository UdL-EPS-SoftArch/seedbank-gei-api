package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class CreateTakeStepDefs {
    String newResourceUri;
    @Autowired
    private StepDefs stepDefs;
    
    @Given("^There is no Take available with id (\\d+)$")
    public void thereIsNoTakeAvailableWithId(Long id) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/takes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("^I create a new Take$")
    public void iCreateANewTake() throws Throwable {
        Take take = new Take();
        // Pasar a la feature
        take.setTakeDate(ZonedDateTime.now());
        take.setAmount(5);
        take.setWeight(new BigDecimal("5"));
        take.setLocation("Lleida");

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(take))
        );
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("^I can retrieve that Take$")
    public void takeHasBeenCreated() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(newResourceUri)
                                .accept(MediaType.APPLICATION_JSON));
    }
}

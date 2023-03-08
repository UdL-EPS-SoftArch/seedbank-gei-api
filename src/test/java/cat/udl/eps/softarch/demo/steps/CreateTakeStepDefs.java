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
    public static String newResourceUri;

    @Autowired
    private StepDefs stepDefs;

    @When("^I create a new Take with amount (\\d+), weight (\\d+) and location \"([^\"]*\")$")
    public void iCreateANewTake(Integer amount, Integer weight, String location) throws Throwable {
        Take take = new Take();
        take.setAmount(amount);
        take.setWeight(new BigDecimal(weight));
        take.setLocation(location);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(take))
                        .with(AuthenticationStepDefs.authenticate())
        );
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("^I can retrieve that Take$")
    public void takeHasBeenCreated() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(newResourceUri)
                                .with(AuthenticationStepDefs.authenticate())
                                .accept(MediaType.APPLICATION_JSON));
    }
}

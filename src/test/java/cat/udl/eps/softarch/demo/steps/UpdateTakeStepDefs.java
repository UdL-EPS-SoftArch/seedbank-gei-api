package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class UpdateTakeStepDefs {
    public static String newResourceUri;
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private TakeRepository takeRepository;

    /*@When("I update the Take with id {long}")
    public void iUpdateTheTake(long id)  throws Exception{
        Take take = new Take();
        take.setTakeDate(ZonedDateTime.now());
        take.setId(id);
        take.setAmount(5);
        take.setWeight(new BigDecimal("5"));
        take.setLocation("Lleida");

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject( stepDefs.mapper.writeValueAsString(take)).toString())
        );
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }*/

    @When("^I update Take$")
    public void iUpdateATake() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new JSONObject( CreateTakeStepDefs.createdTake).toString())
                        .with(AuthenticationStepDefs.authenticate())
        );
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");

    }
    @And("^Take has been updated$")
    public void takeHasBeenUpdated() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get(UpdateTakeStepDefs.newResourceUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        );
    }
}

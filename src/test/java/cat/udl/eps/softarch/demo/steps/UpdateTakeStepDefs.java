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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UpdateTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private TakeRepository takeRepository;


    @Given("There is a Take available with id {long}")
    public void thereIsATakeAvailableWithId(long id) throws Exception {
        Take take = new Take();
        take.setTakeDate(ZonedDateTime.now());
        take.setId(id);
        take.setAmount(5);
        take.setWeight(new BigDecimal("5"));
        take.setLocation("Lleida");

        stepDefs.mockMvc.perform(
                post("/takes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(take)));


        stepDefs.result = stepDefs.mockMvc.perform(
                get("/takes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("I update the Take with id {long}")
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
    }

    @And("The Take with id {long}  has been updated")
    public void theTakeWithIdHasBeenUpdated(long id)  throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/takes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON));
    }
}

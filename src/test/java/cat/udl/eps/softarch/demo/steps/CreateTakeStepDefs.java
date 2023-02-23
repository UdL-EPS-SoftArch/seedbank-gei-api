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

import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class CreateTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private TakeRepository takeRepository;

    @Given("^There is no Take available with id (\\d+)$")
    public void thereIsNoTakeAvailableWithId(Integer id) {
        Assert.assertNull(this.takeRepository.findById(id));
    }

    @When("^I create a new Take")
    public void iCreateANewTake() throws Throwable {
        Take take = new Take();
        take.setDate(ZonedDateTime.now());
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/takes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(String.valueOf(new JSONObject(stepDefs.mapper.writeValueAsString(take)))
                                )
                        );
    }

    @And("^Take has been created with id (\\d+)$")
    public void takeHasBeenCreated(Integer id){
        Assert.assertTrue(this.takeRepository.existsById(id));
    }
}

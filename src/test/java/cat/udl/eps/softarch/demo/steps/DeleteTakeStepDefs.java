package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class DeleteTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;
    private void createNewTake(Long id) throws Throwable {
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
    @Given("^There is Take available with id (\\d+)$")
    public void thereIsTakeAvailableWithId(Long id) throws Throwable {
        createNewTake(id);
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/takes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("^I delete Take with id (\\d+)$")
    public void iDeleteATake(Long id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/takes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
        );
    }
    @And("^Take has been deleted with id (\\d+)$")
    public void takeHasBeenDeleted(Long id) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/takes/{id}", id)
                        .accept(MediaType.APPLICATION_JSON));
    }
}
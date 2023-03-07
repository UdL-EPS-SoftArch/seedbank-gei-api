package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class DeleteTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private TakeRepository takeRepository;

    @And("There is a Take created with amount {int}, weight {int} and location {string}")
    public void thereIsATakeCreatedWithAmountWeightAndLocation(int amount, int weight, String location) {
        Take take = new Take();
        take.setAmount(amount);
        take.setWeight(new BigDecimal(weight));
        take.setLocation(location);
        takeRepository.save(take);
        take = takeRepository.findAll().iterator().next();
        CreateTakeStepDefs.newResourceUri = "/takes/" + take.getId();
    }
    @When("^I delete Take$")
    public void iDeleteATake() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete(CreateTakeStepDefs.newResourceUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        );
    }
    @And("^Take has been deleted$")
    public void takeHasBeenDeleted() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get(CreateTakeStepDefs.newResourceUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        );
    }

}
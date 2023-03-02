package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class DeleteTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;
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
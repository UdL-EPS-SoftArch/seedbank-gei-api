package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class DeleteSeedStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @When("I delete Seed with id {int}")
    public void iDeleteSeedWithId(int id) throws Throwable  {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/seeds/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        );
    }
}
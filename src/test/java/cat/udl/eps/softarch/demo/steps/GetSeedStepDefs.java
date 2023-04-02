package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class GetSeedStepDefs {


    private StepDefs stepDefs;

    @When("^I try to retrieve {int} Seed")
    public void retrieveSeed(int id) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/seed/{id}", id)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("I try to retrieve a Seed with id {int}")
    public void iTryToRetrieveASeedWithId(Long id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/seeds/{id}", id)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }
}

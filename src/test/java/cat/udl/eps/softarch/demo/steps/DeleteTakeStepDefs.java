package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class DeleteTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;

    private void deleteTake() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete(CreateTakeStepDefs.newResourceUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        );
    }

    @When("^I try to delete Take$")
    public void iTryToDeleteATake() throws Exception {
        deleteTake();
    }

    @When("I try to delete Take with id {int}")
    public void iTryToDeleteTakeWithId(int id) throws Exception {
        CreateTakeStepDefs.newResourceUri = "/takes/" + id;
        deleteTake();
    }
}
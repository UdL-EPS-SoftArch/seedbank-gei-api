package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Seed;
import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class GetSeedStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private SeedRepository seedRepository;

    @When("^I try to retrieve (\\d+) Seed$")
    public void retrieveSeed(int id) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/seed/{id}", id)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("I try to retrieve a seed with scientific name \"([^\"]*)\"$")
    public void iTryToRetrieveASeedWithId(String scientificName) throws Throwable {
        var optionalSeed = seedRepository.findByScientificName(scientificName);
        var identifier = optionalSeed.map(Seed::getId).orElse(-1L); // arbitrary value for non-existent seed
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/seeds/{id}", identifier)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)).andDo(print());
    }
}

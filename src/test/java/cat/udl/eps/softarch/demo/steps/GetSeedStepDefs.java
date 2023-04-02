package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Seed;
import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        Optional<Seed> optionalSeed = seedRepository.findByScientificName(scientificName);
        stepDefs.result = stepDefs.mockMvc.perform(
            get("/seeds/{id}", optionalSeed.isPresent() ? optionalSeed.get().getId() : "999")
                .with(AuthenticationStepDefs.authenticate())
                .accept(MediaType.APPLICATION_JSON));
    }
}

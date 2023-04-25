package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Seed;
import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteSeedStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private SeedRepository seedRepository;

    @Transactional
    @When("I delete the seed with scientific name \"([^\"]*)\"$")
    public void iDeleteSeedWithId(String scientificName) throws Throwable {
        var seed = seedRepository.findByScientificName(scientificName);
        var identifier = seed.map(Seed::getId).orElse(-1L); // arbitrary value for non-existent seed
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/seeds/{id}", identifier)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }
}

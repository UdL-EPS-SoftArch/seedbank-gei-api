package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Seed;
import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CreateSeedStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private SeedRepository seedRepository;
    public static String newResourceUri;

    @When("I create a new Seed with scientificName {string} and commonName {string}")
    public void iCreateANewSeedWithScientificNameAndCommonName(String scientificName, String commonName) throws Throwable {
        Seed seed = new Seed();
        seed.setScientificName(scientificName);
        List<String> commonNames = Arrays.asList(commonName.split(", ", -1));
        seed.setCommonName(commonNames);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/seeds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(seed))
                        .with(AuthenticationStepDefs.authenticate())

        );
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("There is already a Seed with id {int}, scientificName {string} and commonName {string}")
    public void thereIsAlreadyASeedWithIdScientificNameAndCommonName(Long id, String scientificName, String commonName) throws Throwable {
        if(seedRepository.existsById(id)) {
            return;
        }
        Seed seed = new Seed();
        seed.setId(id);
        seed.setScientificName(scientificName);
        List<String> commonNames = Arrays.asList(commonName.split(", ", -1));
        seed.setCommonName(commonNames);
        seedRepository.save(seed);
    }

    @And("There is {int} Seed created")
    public void thereIsSeedCreated(int seedCreatedNum) {
        Assert.assertEquals(seedCreatedNum, seedRepository.count());
    }

    @And("I try to retrieve that Seed")
    public void retrieveSeed() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform (
                get(newResourceUri)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("^I create a new Seed with empty body$")
    public void createSeedWithEmptyBody() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/seed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(null))
                        .with(AuthenticationStepDefs.authenticate())
        );
    }
}

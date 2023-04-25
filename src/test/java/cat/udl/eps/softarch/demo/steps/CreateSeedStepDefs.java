package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.mothers.SeedMother;
import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateSeedStepDefs {
    public static String newResourceUri;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private SeedRepository seedRepository;

    @When("I create a new Seed with scientificName \"([^\"]*)\" and commonName \"([^\"]*)\"$")
    public void iCreateANewSeedWithScientificNameAndCommonName(String scientificName, String commonName) throws Throwable {
        var seed = SeedMother.getSeed(scientificName, commonName);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/seeds")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(seed))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("There is already a seed with scientificName \"([^\"]*)\" and commonName \"([^\"]*)\"$")
    public void thereIsAlreadyASeedWithIdScientificNameAndCommonName(String scientificName, String commonName) {
        var seed = SeedMother.getSeed(scientificName, commonName);
        seedRepository.save(seed);
    }

    @And("There is (\\d+) Seed created$")
    public void thereIsSeedCreated(int seedCreatedNum) {
        Assert.assertEquals(seedCreatedNum, seedRepository.count());
    }

    @And("I try to retrieve that Seed")
    public void retrieveSeed() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get(newResourceUri)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @When("^I create a new Seed with empty body$")
    public void createSeedWithEmptyBody() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/seeds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(null))
                        .with(AuthenticationStepDefs.authenticate())
        );
    }
}

package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Seed;
import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateSeedStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private SeedRepository seedRepository;
    public static String newResourceUri;

    @When("I create a new Seed with scientificName \"([^\"]*)\" and commonName \"([^\"]*)\"$")
    public void iCreateANewSeedWithScientificNameAndCommonName(String scientificName, String commonName) throws Throwable {
        Seed seed = new Seed();
        seed.setScientificName(scientificName);
        List<String> commonNames = Arrays.asList(commonName.split(", ", -1));
        seed.setCommonName(commonNames);

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
    public void thereIsAlreadyASeedWithIdScientificNameAndCommonName(String scientificName, String commonName) throws Throwable {
        Seed seed = new Seed();
        seed.setScientificName(scientificName);
        List<String> commonNames = Arrays.asList(commonName.split(", ", -1));
        seed.setCommonName(commonNames);
        seedRepository.save(seed);
    }

    @And("There is (\\d+) Seed created$")
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
                post("/seeds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(null))
                        .with(AuthenticationStepDefs.authenticate())
        );
    }
}

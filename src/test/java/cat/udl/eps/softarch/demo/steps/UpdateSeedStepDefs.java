package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Seed;
import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

public class UpdateSeedStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private SeedRepository seedRepository;
    @When("I update Seed with id {int} by changing scientificName to {string} and commonName to {string}")
    public void iUpdateSeedWithIdByChangingScientificNameToAndCommonNameTo(int id, String scientificName, String commonName) throws Throwable {
        Seed seed = seedRepository.findById(id).get(0);
        Long seedId = seed.getId();

        JSONObject modifySeed = new JSONObject();
        modifySeed.put("scientificName", scientificName);
        modifySeed.put("commonName", Arrays.asList(commonName.split(", ", -1)));

        stepDefs.result = stepDefs.mockMvc.perform(patch("/seeds/{id}", seedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(modifySeed.toString())
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate()));

        JSONObject updateSeedJSON = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
        Assert.assertEquals(scientificName, updateSeedJSON.get("scientificName"));
        Assert.assertEquals(Arrays.asList(commonName.split(", ", -1)), updateSeedJSON.get("scientificName"));
    }

    @When("I update Seed with id {int} by changing scientificName to {string}")
    public void iUpdateSeedWithIdByChangingScientificNameTo(int id, String scientificName) throws Throwable{
        Seed seed = seedRepository.findById(id).get(0);
        Long seedId = seed.getId();

        JSONObject modifySeed = new JSONObject();
        modifySeed.put("scientificName", scientificName);

        stepDefs.result = stepDefs.mockMvc.perform(patch("/seeds/{id}", seedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(modifySeed.toString())
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate()));

        JSONObject updateSeedJSON = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
        Assert.assertEquals(scientificName, updateSeedJSON.get("scientificName"));
    }
}

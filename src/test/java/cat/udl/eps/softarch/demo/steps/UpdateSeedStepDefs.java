package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateSeedStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private SeedRepository seedRepository;

    @Transactional
    @When("I update seed with scientific name \"([^\"]*)\" by changing it to \"([^\"]*)\" and commonName to \"([^\"]*)\"$")
    public void iUpdateSeedWithIdByChangingScientificNameToAndCommonNameTo(
            String scientificName, String newScientificName, String commonName) throws Throwable {
        var optionalSeed = seedRepository.findByScientificName(scientificName);

        JSONObject modifySeed = new JSONObject();
        modifySeed.put("scientificName", newScientificName);
        if (commonName != null)
            modifySeed.put("commonName", new JSONArray(commonName.split(", ", -1)));

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/seeds/{id}", optionalSeed.isPresent() ? optionalSeed.get().getId() : "999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifySeed.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .with(AuthenticationStepDefs.authenticate())).andDo(print());

        if (stepDefs.result.andReturn().getResponse().getStatus() == 200) {
            JSONObject updateSeedJSON = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
            Assert.assertEquals(newScientificName, updateSeedJSON.get("scientificName"));
            if (commonName != null)
                Assert.assertEquals(new JSONArray(commonName.split(", ", -1)), updateSeedJSON.get("commonName"));
        }
    }

    @When("I update seed with scientific name \"([^\"]*)\" by changing it to \"([^\"]*)\"$")
    public void iUpdateSeedWithIdByChangingScientificNameTo(
            String scientificName, String newScientificName) throws Throwable {
        iUpdateSeedWithIdByChangingScientificNameToAndCommonNameTo(scientificName, newScientificName, null);
    }
}

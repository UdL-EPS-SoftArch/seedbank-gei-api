package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.sk.Tak;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class UpdateTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;


    @When("^I update Take with changing amount to (\\d+)$")
    public void iUpdateTakeWithChangingAmountTo(Integer newAmount)throws Throwable{
        JSONObject modifyTake = new JSONObject();
        modifyTake.put("amount", newAmount);
        stepDefs.result = stepDefs.mockMvc.perform(patch(CreateTakeStepDefs.newResourceUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyTake.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()));

        JSONObject updateTakeJSON = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
        Assert.assertEquals(newAmount, updateTakeJSON.get("amount"));
    }
    
}

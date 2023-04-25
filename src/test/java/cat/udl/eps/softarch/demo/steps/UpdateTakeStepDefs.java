package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.sk.Tak;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class UpdateTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;

    private int newAmount;


    @When("^I update Take changing amount to (\\d+)$")
    public void iUpdateTakeChangingAmountTo(int newAmount) throws Throwable {
        this.newAmount = newAmount;
        var modifyTake = new JSONObject();
        modifyTake.put("amount", newAmount);
        stepDefs.result = stepDefs.mockMvc.perform(patch(CreateTakeStepDefs.newResourceUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyTake.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()));
    }

    @And("^I check that Take has been succesfully updated")
    public void iCheckThatTakeHasBeenSuccesfullyUpdated() throws Throwable {
        var updateTakeJSON = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
        Assert.assertEquals(newAmount, updateTakeJSON.get("amount"));
    }

    @When("^I update Take with id (\\d+) changing amount to (\\d+)")
    public void iUpdateTakeWithIdChangingAmountTo(int id, int amount) throws Throwable {
        CreateTakeStepDefs.newResourceUri = "/takes/" + id;
        iUpdateTakeChangingAmountTo(amount);
    }
    
}

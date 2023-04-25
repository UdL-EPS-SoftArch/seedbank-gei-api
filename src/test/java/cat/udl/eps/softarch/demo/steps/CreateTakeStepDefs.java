package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.mothers.TakeMother;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import java.util.Iterator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class CreateTakeStepDefs {
    public static String newResourceUri;
    @Autowired
    private TakeRepository takeRepository;
    @Autowired
    private PropagatorRepository propagatorRepository;

    @Autowired
    private StepDefs stepDefs;
    @When("^I create a valid take")
    public void iCreateANewTake() throws Throwable {
        var take = TakeMother.getValidTake();
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(stepDefs.mapper.writeValueAsString(take))
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("^I try to retrieve that Take$")
    public void iTryToRetrieveThatTake() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(newResourceUri)
                                .with(AuthenticationStepDefs.authenticate())
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON));
    }

    @When("^I try to retrieve Take with id (\\d+)$")
    public void iTryToRetrieveTakeWithId(int id) throws Exception {
        newResourceUri = "/takes/" + id;
        stepDefs.result = stepDefs.mockMvc.perform(
                get(newResourceUri)
                        .with(AuthenticationStepDefs.authenticate())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON));
    }

    @And("There is a valid take saved on the database")
    public void thereIsATakeCreatedWithAmountWeightAndLocation() {
        var take = TakeMother.getValidTake();
        takeRepository.save(take);
        CreateTakeStepDefs.newResourceUri = "/takes/" + take.getId();
    }
    
    @When("^I create a new Take with empty body$")
    public void createTakeEmptyBody() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/takes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(stepDefs.mapper.writeValueAsString(null))
                        .with(AuthenticationStepDefs.authenticate())
        );
    }
}

package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.mothers.DonorMother;
import cat.udl.eps.softarch.demo.mothers.PropagatorMother;
import cat.udl.eps.softarch.demo.mothers.UserMother;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterStepDefs {

    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private TakeRepository takeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropagatorRepository propagatorRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Given("^There is no registered user with username \"([^\"]*)\"$")
    public void thereIsNoRegisteredUserWithUsername(String user) {
        Assert.assertFalse("User \""
                        + user + "\"shouldn't exist",
                userRepository.existsById(user));
    }

    @Given("^There is a registered user with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredUserWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        registerUser(() -> UserMother.getUserWithEncodingPassword(username, password, email));
    }

    @Given("^There is a valid registered user with username \"([^\"]*)\"")
    public void thereIsAValidRegisteredUserWithUserName(String username) {
        registerUser(() -> UserMother.getUserWithEncodingPassword(username));
    }

    @And("^I can login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iCanLoginWithUsernameAndPassword(String username, String password) throws Throwable {
        stepDefs.result = loginUser(username, password, StatusResultMatchers::isOk);
    }

    @And("^I cannot login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iCannotLoginWithUsernameAndPassword(String username, String password) throws Throwable {
        stepDefs.result = loginUser(username, password, StatusResultMatchers::isUnauthorized);
    }

    @When("^I register a new user with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iRegisterANewUserWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
        registerUserViaApi(() -> UserMother.getUser(username, password, email));
    }

    @When("^I register a new valid user with username \"([^\"]*)\"")
    public void iRegisterANewUserWithUsernameEmailAndPassword(String username) throws Throwable {
        registerUserViaApi(() -> UserMother.getUser(username));
    }


    @And("^It has been created a user with username \"([^\"]*)\" and email \"([^\"]*)\", the password is not returned$")
    public void itHasBeenCreatedAUserWithUsername(String username, String email) throws Throwable {
        stepDefs.result = callApiWithAuthentication(get("/users/{username}", username))
                .andDo(print())
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @And("^It has not been created a user with username \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAUserWithUsername(String username) throws Throwable {
        stepDefs.result = callApiWithAuthentication(get("/users/{username}", username))
                .andExpect(status().isNotFound());
    }

    @Given("^There is no registered propagator with username \"([^\"]*)\"$")
    public void thereIsNoRegisteredPropagatorWithUsername(String user) {
        Assert.assertFalse("Propagator \""
                        + user + "\"shouldn't exist",
                propagatorRepository.existsById(user));
    }
// (.*?)
    @Given("^There is a registered propagator with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\" with the following takes$")
    public void thereIsARegisteredPropagatorWithUsernameAndPasswordAndEmailAndListOfTakes(String username, String password, String email) {
        Take take = new Take();
        take.setAmount(1);
        take.setWeight(new BigDecimal(5));
        take.setLocation("Lleida");
        takeRepository.save(take);
        ArrayList<Take> takes = new ArrayList<>();
        takes.add(take);
        registerPropagator(() -> PropagatorMother.getValidPropagatorWith(username, password, email, takes));
    }

    @Given("^There is a valid registered propagator with username \"([^\"]*)\"")
    public void registerValidPropagatorWithUserName(String username) {
        registerPropagator(() -> PropagatorMother.getValidPropagatorWith(username));
    }

    //@When("^I register a new propagator with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\" with the following takes:$")
    //public void iRegisterANewPropagatorWithUsernameEmailAndPasswordAndListOfTakes(String username, String email, String password, ArrayList<Take> listOfTakes) throws Throwable {
    //    registerUserViaApi(() -> PropagatorMother.getValidPropagatorWith(username, password, email, listOfTakes));
    //}

    @Given("^There is no registered donor with username \"([^\"]*)\"$")
    public void thereIsNoRegisteredDonorWithUsername(String user) {
        Assert.assertFalse("Donor \""
                        + user + "\"shouldn't exist",
                donorRepository.existsById(user));
    }

    @Given("^There is a registered donor with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredDonorWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        registerDonor(() -> DonorMother.getValidDonorWith(username, password, email));
    }


    @Given("^There is a valid registered donor with username \"([^\"]*)\"")
    public void registerValidDonorWithUserName(String username) {
        registerDonor(() -> DonorMother.getValidDonorWith(username));
    }

    @When("^I register a new donor with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iRegisterANewDonorWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
        registerUserViaApi(() -> DonorMother.getValidDonorWith(username, password, email));
    }


    private void registerUser(Supplier<User> userGenerator) {
        register(userGenerator.get(), userRepository);
    }

    private void registerDonor(Supplier<Donor> donorGenerator) {
        register(donorGenerator.get(), donorRepository);
    }

    private void registerPropagator(Supplier<Propagator> propagatorGenerator) {
        register(propagatorGenerator.get(), propagatorRepository);
    }

    private <T extends Persistable<ID>, ID> void register(T value, CrudRepository<T, ID> repo) {
        if (!repo.existsById(Objects.requireNonNull(value.getId())))
            repo.save(value);
    }

    private void registerUserViaApi(Supplier<User> userGenerator) throws Exception {
        var user = userGenerator.get();
        var userPost = post("/users");
        var userPostWithUserContent = getRequestWithContent(userPost, getJSONRequestFrom(user));
        stepDefs.result = callApiWithAuthentication(userPostWithUserContent)
                .andDo(print());
    }

    private ResultActions callApiWithAuthentication(MockHttpServletRequestBuilder request) throws Exception {
        return stepDefs.mockMvc.perform(getRequestWithAuthentication(request));
    }

    private MockHttpServletRequestBuilder getRequestWithAuthentication(MockHttpServletRequestBuilder request) {
        return request.contentType(MediaType.APPLICATION_JSON).
                with(AuthenticationStepDefs.authenticate());
    }

    private MockHttpServletRequestBuilder getRequestWithContent(MockHttpServletRequestBuilder request, String content) {
        return request.content(content);
    }

    private String getJSONRequestFrom(User user) throws JsonProcessingException, JSONException {
        return new JSONObject(
                stepDefs.mapper.writeValueAsString(user)
        ).put("password", user.getPassword()).toString();
    }

    private ResultActions loginUser(String username, String password, Function<StatusResultMatchers, ResultMatcher> validator) throws Exception {
        AuthenticationStepDefs.currentUsername = username;
        AuthenticationStepDefs.currentPassword = password;
        return callApiWithAuthentication(get("/identity", username))
                .andDo(print())
                .andExpect(validator.apply(status()));
    }
}

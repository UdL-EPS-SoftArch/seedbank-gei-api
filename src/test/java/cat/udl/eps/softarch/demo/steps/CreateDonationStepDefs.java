package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.mothers.DonationMother;
import cat.udl.eps.softarch.demo.mothers.TakeMother;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class CreateDonationStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private TakeRepository takeRepository;
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;

    private Take take;
    private Donor donor;


    @And("User {string} is a donor")
    public void thereIsAValidDonor(String name) {
        this.donor = donorRepository.findById(name).orElseThrow();
    }

    @And("A take action exists")
    public void thereIsAValidTake() {
        take = TakeMother.getValidTake();
        takeRepository.save(take);
    }

    @When("The donor can create a donation from the take action")
    public void createDonation() throws Exception {
        assertNotNull(donor);
        assertNotNull(take);
        var donation = DonationMother.getValidDonationFor(donor, take);
        stepDefs.result = stepDefs.mockMvc.perform(post("/donations").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8").content(stepDefs.mapper.writeValueAsString(donation)).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

    @But("There is no Donor")
    public void thereIsNoValidDonor() {
        assertNull(donor);
    }

    @But("There is no Take")
    public void thereIsNoValidTake() {
        assertNull(take);
    }

    @And("There is {int} donation created")
    public void thereIsDonationCreated(int numDonations) {
        Assert.assertEquals(numDonations, donationRepository.count());
    }

    @When("I create a new donation with the take action")
    public void createDonationWithoutDonor() throws Exception {
        assertNotNull(take);
        var donation = DonationMother.getDonationOnlyWith(take);
        stepDefs.result = stepDefs.mockMvc.perform(post("/donations").contentType(MediaType.APPLICATION_JSON).content(stepDefs.mapper.writeValueAsString(donation)).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8").with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

    @When("I create a new donation from the donor but without a take action")
    public void createDonationWithoutTake() throws Exception {
        assertNotNull(donor);
        var donation = DonationMother.getDonationOnlyWith(donor);
        stepDefs.result = stepDefs.mockMvc.perform(post("/donations").contentType(MediaType.APPLICATION_JSON).content(stepDefs.mapper.writeValueAsString(donation)).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8").with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

}

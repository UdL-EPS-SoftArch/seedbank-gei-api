package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.mothers.DonationMother;
import cat.udl.eps.softarch.demo.mothers.TakeMother;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.DonorRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertNull;
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


    @And("User {string} is the donor")
    public void thereIsAValidDonor(String name) {
        this.donor = donorRepository.findById(name).orElseThrow();
    }

    @And("A valid take action exists")
    public void thereIsAValidTake() {
        this.take = TakeMother.getValidTake();
        takeRepository.save(this.take);
    }

    @When("The donor creates a donation from the take action")
    public void createDonation() throws Exception {
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
}

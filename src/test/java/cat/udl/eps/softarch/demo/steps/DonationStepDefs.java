package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donation;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class DonationStepDefs {
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

    private Donation donation;


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
        donation = DonationMother.getValidDonationFor(donor, take);
        stepDefs.result = stepDefs.mockMvc.perform(post("/donations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(stepDefs.mapper.writeValueAsString(donation))
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate())).andDo(print());
        donation = donationRepository.findAll().iterator().next();
    }

    @But("There is no Donor")
    public void thereIsNoValidDonor() {
        assertNull(donor);
    }

    @But("There is no Take")
    public void thereIsNoValidTake() {
        assertEquals(0, takeRepository.count());
    }

    @And("There is {int} donation created")
    public void thereIsDonationCreated(int numDonations) {
        assertEquals(numDonations, donationRepository.count());
    }

    @When("The donor removes the donation")
    public void theDonorRemovesTheDonation() throws Exception {
        var myballs  = donationRepository.findAll();
        stepDefs.result = stepDefs.mockMvc.perform(delete("/donations/{id}", donation.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(stepDefs.mapper.writeValueAsString(donation))
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate())).andDo(print());
    }
}

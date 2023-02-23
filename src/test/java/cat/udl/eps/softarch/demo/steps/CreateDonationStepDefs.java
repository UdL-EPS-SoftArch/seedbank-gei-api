package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class CreateDonationStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TakeRepository takeRepository;
    @Autowired
    private DonationRepository donationRepository;

    Donor donor;
    Take take;

    @And("There is a valid Donor")
    public void thereIsAValidDonor() {
        donor = createValidDonor();
        userRepository.save(donor);
        Assert.assertNotEquals(0, userRepository.count());
    }

    @And("There is a valid Take")
    public void thereIsAValidTake() {
        take = createValidTake();
        takeRepository.save(take);
        Assert.assertEquals(1, takeRepository.count());
    }

    @When("I create a new donation")
    public void createDonation() throws Exception {
        Donation donation = createValidDonation();

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/donations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(donation))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("There is {int} donation created")
    public void thereIsDonationCreated(int numDonations) {
        Assert.assertEquals(numDonations, donationRepository.count());
    }


    private Donor createValidDonor() {
        Donor donor = new Donor();
        donor.setUsername("iAmADonor");
        donor.setPassword("donorPassword");
        donor.encodePassword();
        donor.setEmail("donor@donor.donor");
        return donor;
    }

    private Take createValidTake() {
        Take take = new Take();
        take.setAmount(6);
        take.setWeight(BigDecimal.TEN);
        take.setLocation("Barcelona");
        take.setDate(ZonedDateTime.now());
        return take;
    }

    private Donation createValidDonation() {
        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setTakenBy(take);
        donation.setAmount(1);
        donation.setWeight(BigDecimal.ONE);
        donation.setDate(ZonedDateTime.now());
        return donation;
    }
}

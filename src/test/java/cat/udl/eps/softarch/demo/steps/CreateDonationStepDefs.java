package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
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

    @And("There is a valid Donor")
    public void thereIsAValidDonor() {
        Donor donor = createValidDonor();
        userRepository.save(donor);
        Assert.assertNotEquals(0, userRepository.count());
    }

    @And("There is a valid Take")
    public void thereIsAValidTake() {
        Take take = createValidTake();
        takeRepository.save(take);
        Assert.assertNotEquals(0, takeRepository.count());
    }

    @When("I create a new valid donation")
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

    @But("There is no Donor")
    public void thereIsNoValidDonor() {
        for (User user : userRepository.findAll()) {
            Assert.assertFalse(user instanceof Donor);
        }
    }

    @But("There is no Take")
    public void thereIsNoValidTake() {
        assert takeRepository.count() == 0;
    }

    @And("There is {int} donation created")
    public void thereIsDonationCreated(int numDonations) {
        Assert.assertEquals(numDonations, donationRepository.count());
    }

    @When("I create a new donation without a donor")
    public void createDonationWithoutDonor() throws Exception {
        Donation donation = donationWithoutDonor();
        donation.setDonor(null);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/donations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(donation))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I create a new donation without a take")
    public void createDonationWithoutTake() throws Exception {
        Donation donation = donationWithoutTake();
        donation.setTakenBy(null);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/donations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(donation))
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
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
        donation.setDonor(createValidDonor());
        donation.setTakenBy(createValidTake());
        donation.setAmount(1);
        donation.setWeight(BigDecimal.ONE);
        donation.setDate(ZonedDateTime.now());
        return donation;
    }

    private Donation donationWithoutDonor() {
        Donation donation = new Donation();
        donation.setDonor(null);
        donation.setTakenBy(createValidTake());
        donation.setAmount(1);
        donation.setWeight(BigDecimal.ONE);
        donation.setLocation("Barcelona");
        donation.setDate(ZonedDateTime.now());
        return donation;
    }

    private Donation donationWithoutTake() {
        Donation donation = new Donation();
        donation.setDonor(createValidDonor());
        donation.setTakenBy(null);
        donation.setAmount(1);
        donation.setWeight(BigDecimal.ONE);
        donation.setLocation("Barcelona");
        donation.setDate(ZonedDateTime.now());
        return donation;
    }
}

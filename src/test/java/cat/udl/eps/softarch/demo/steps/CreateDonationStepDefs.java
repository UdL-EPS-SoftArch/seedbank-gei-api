package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class RegisterDonationStepDefs {
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private UserRepository userRepository;

    private Donor donor;
    private Take take;

    @And("There is a valid Donor")
    public void thereIsAValidDonor() {
        donor = createValidDonor();
        Take take = createValidTake();
    }

    @And("There is a valid Take")
    public void thereIsAValidTake() {
        take = createValidTake();
    }

    @When()


    private Donor createValidDonor() {
        Donor donor = new Donor();
        donor.setUsername("donor");
        donor.setPassword("donor");
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
}

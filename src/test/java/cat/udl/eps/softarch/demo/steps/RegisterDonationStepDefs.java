package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.http.RequestEntity.post;

public class RegisterDonationStepDefs {
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private UserRepository userRepository;

    @And("There is a valid Donor")
    public void thereIsAValidDonor() {
        Donor donor = new Donor();
        donor.setUsername("donor");
        donor.setPassword("donor");
        donor.setEmail("donor@donor.donor");
        userRepository.save(donor);
    }
}

package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.mothers.DonationMother;
import cat.udl.eps.softarch.demo.mothers.DonorMother;
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

    @And("There is a valid Donor with name {string}")
    public void thereIsAValidDonor(String name) {
        Donor donor = DonorMother.getValidDonorWithName(name);
        donorRepository.save(donor);
    }

    @And("There is a valid Take with identifier {long}")
    public void thereIsAValidTake(long takeId) {
        var take = TakeMother.getValidTake();
        takeRepository.save(take);
    }

    @When("I create a new valid donation with id: {long} from donor {string} and take {long}")
    public void createDonation(long id, String donorName, long takeId) throws Exception {
        var donor = donorRepository.findById(donorName).orElseThrow();
        var take = takeRepository.findById(takeId).orElseThrow();
        var donation = DonationMother.getValidDonationFor(donor, take);
        stepDefs.result = stepDefs.mockMvc.perform(post("/donations").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8").content(stepDefs.mapper.writeValueAsString(donation)).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

    @But("There is no Donor")
    public void thereIsNoValidDonor() {
        // TODO: refactor this
        for (User user : donorRepository.findAll())
            Assert.assertFalse(user instanceof Donor);
    }

    @But("There is no Take")
    public void thereIsNoValidTake() {
        assert takeRepository.count() == 0;
    }

    @And("There is {int} donation created")
    public void thereIsDonationCreated(int numDonations) {
        Assert.assertEquals(numDonations, donationRepository.count());
    }

    @When("I create a new donation without a donor and a take with id {long}")
    public void createDonationWithoutDonor(long takeId) throws Exception {
        var take = takeRepository.findById(takeId).orElseThrow();
        var donation = DonationMother.getDonationOnlyWith(take);
        stepDefs.result = stepDefs.mockMvc.perform(post("/donations").contentType(MediaType.APPLICATION_JSON).content(stepDefs.mapper.writeValueAsString(donation)).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8").with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

    @When("I create a new donation with id {long} without a take and with a donor name {string}")
    public void createDonationWithoutTake(long id, String name) throws Exception {
        var donor = donorRepository.findById(name).orElseThrow();
        var donation = DonationMother.getDonationOnlyWith(donor);
        stepDefs.result = stepDefs.mockMvc.perform(post("/donations").contentType(MediaType.APPLICATION_JSON).content(stepDefs.mapper.writeValueAsString(donation)).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8").with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

}

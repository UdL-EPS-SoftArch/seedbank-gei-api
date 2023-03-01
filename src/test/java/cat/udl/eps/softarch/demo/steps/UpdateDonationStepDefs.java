package cat.udl.eps.softarch.demo.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class UpdateDonationStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @When("^I update the donation name")
    public void updateDonationName() {
        throw new PendingException();
    }

    @And("^There is a registered admin with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredAdminWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        throw new PendingException();
    }
}

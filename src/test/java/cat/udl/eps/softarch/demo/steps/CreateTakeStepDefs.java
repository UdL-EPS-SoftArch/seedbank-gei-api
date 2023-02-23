package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import io.cucumber.java.en.Given;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateTakeStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private TakeRepository takeRepository;

    @Given("^There is no Take available with id (\\d+)$")
    public void thereIsNoTakeAvailableWithId(Integer id) {
        Assert.assertNull(this.takeRepository.findById(id));
    }
}

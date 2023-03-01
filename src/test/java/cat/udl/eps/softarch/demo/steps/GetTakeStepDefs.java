package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.repository.TakeRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class GetTakeStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private TakeRepository takeRepository;
}

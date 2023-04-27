package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Take;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TakeMother {

    public static Take getValidTake() {
        var take = new Take();
        take.setAmount(6);
        take.setWeight(BigDecimal.TEN);
        take.setLocation("Barcelona");
        take.setDate(ZonedDateTime.now());
        return take;
    }

}

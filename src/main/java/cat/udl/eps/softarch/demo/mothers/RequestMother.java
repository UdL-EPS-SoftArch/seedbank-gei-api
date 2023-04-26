package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.Take;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class RequestMother {
    public static Request getValidRequestFor(@Nullable Propagator propagator, @Nullable Take take) {
        var request = new Request();
        request.setPropagator(propagator);
        request.setFulfilledBy(take);
        request.setAmount(1);
        request.setWeight(BigDecimal.ONE);
        request.setLocation("Barcelona");
        request.setDate(ZonedDateTime.now());
        return request;
    }
}

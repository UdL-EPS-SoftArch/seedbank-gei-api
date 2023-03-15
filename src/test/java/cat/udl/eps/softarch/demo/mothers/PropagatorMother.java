package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Take;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PropagatorMother {
    private static final String VALID_PASSWORD = "password";
    private static final String VALID_EMAIL = "propagator@sample.com";
    private static final ArrayList<Take> VALID_LIST_OF_TAKES = new ArrayList<>(List.of(TakeMother.getValidTake()));

    public static Propagator getValidPropagatorWith(@Nullable String name, @Nullable String password, @Nullable String email, @Nullable ArrayList<Take> listOfTakes) {
        var propagator = new Propagator();
        propagator.setUsername(name);
        propagator.setPassword(password);
        propagator.setEmail(email);
        propagator.encodePassword();
        propagator.setTakes(listOfTakes);
        return propagator;
    }

    public static Propagator getValidPropagatorWith(@Nullable String name) {
        return getValidPropagatorWith(name, VALID_PASSWORD, VALID_EMAIL, VALID_LIST_OF_TAKES);
    }
}

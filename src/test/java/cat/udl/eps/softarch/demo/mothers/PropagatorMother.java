package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Propagator;
import org.jetbrains.annotations.Nullable;

public class PropagatorMother {
    private static final String VALID_PASSWORD = "password";
    private static final String VALID_EMAIL = "propagator@sample.com";
    //private static final ArrayList<Take> VALID_LIST_OF_TAKES = new ArrayList<>(List.of(TakeMother.getValidTake()));

    public static Propagator getValidPropagatorWith(@Nullable String name, @Nullable String password, @Nullable String email) {
        var propagator = new Propagator();
        propagator.setUsername(name);
        propagator.setPassword(password);
        propagator.setEmail(email);
        propagator.encodePassword();
        //propagator.setTakes(listOfTakes);
        return propagator;
    }

    public static Propagator getValidPropagatorWith(@Nullable String name) {
        return getValidPropagatorWith(name, VALID_PASSWORD, VALID_EMAIL);
    }
}

package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Donor;
import org.jetbrains.annotations.Nullable;

public class DonorMother {

    private static final String VALID_PASSWORD = "password";
    private static final String VALID_EMAIL = "donor@sample.com";

    public static Donor getValidDonorWith(@Nullable String name, @Nullable String password, @Nullable String email) {
        var donor = new Donor();
        donor.setUsername(name);
        donor.setPassword(password);
        donor.setEmail(email);
        donor.encodePassword();
        return donor;
    }

    public static Donor getValidDonorWith(@Nullable String name) {
        return getValidDonorWith(name, VALID_PASSWORD, VALID_EMAIL);
    }

}

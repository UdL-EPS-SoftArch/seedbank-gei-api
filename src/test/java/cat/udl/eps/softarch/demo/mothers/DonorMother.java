package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Donor;
import org.jetbrains.annotations.Nullable;

public class DonorMother {
    public static Donor getValidDonorWith(@Nullable String name, @Nullable String password, @Nullable String email) {
        Donor donor = new Donor();
        donor.setUsername(name);
        donor.setPassword(password);
        donor.setEmail(email);
        donor.encodePassword();
        return donor;
    }

}

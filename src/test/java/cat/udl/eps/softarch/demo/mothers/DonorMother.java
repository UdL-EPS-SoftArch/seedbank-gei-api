package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Donor;

public class DonorMother {
    public static final String ValidDonorUsername = "iAmADonor";
    private static final String ValidDonorPassword = "donorPassword";
    private static final String ValidDonorEmail = "donor@donor.donor";

    public static Donor getValidDonorWithName(String name) {
        Donor donor = new Donor();
        donor.setUsername(name);
        donor.setEmail(ValidDonorEmail);
        donor.setPassword(ValidDonorPassword);
        donor.encodePassword();
        return donor;
    }

    public static Donor getValidDonor() {
        return getValidDonorWithName(ValidDonorUsername);
    }

}

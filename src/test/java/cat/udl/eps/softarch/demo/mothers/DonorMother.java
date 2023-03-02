package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Donor;

public class DonorMother {
    public static final String ValidDonorUsername = "iAmADonor";
    private static final String ValidDonorPassword = "donorPassword";
    private static final String ValidDonorEmail = "donor@donor.donor";

    public static Donor getValidDonorWithNameAndPassword(String name, String password) {
        return getValidDonorWith(name, password, ValidDonorEmail);
    }

    public static Donor getValidDonorWith(String name, String password, String email) {
        Donor donor = new Donor();
        donor.setUsername(name);
        donor.setPassword(password);
        donor.setEmail(email);
        donor.encodePassword();
        return donor;
    }

    public static Donor getValidDonorWithName(String name) {
        return getValidDonorWithNameAndPassword(name, ValidDonorPassword);
    }

    public static Donor getValidDonorWithPassword(String password) {
        return getValidDonorWithNameAndPassword(ValidDonorUsername, password);
    }

    public static Donor getValidDonor() {
        return getValidDonorWithNameAndPassword(ValidDonorUsername, ValidDonorPassword);
    }

}

package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Take;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class DonationMother {

    public static Donation getValidDonationFor(Donor donor, Take take) {
        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setTakenBy(take);
        donation.setAmount(1);
        donation.setWeight(BigDecimal.ONE);
        donation.setLocation("Barcelona");
        donation.setDate(ZonedDateTime.now());
        return donation;
    }

    public static Donation getDonationOnlyWith(Donor donor) {
        return DonationMother.getValidDonationFor(donor, null);
    }

    public static Donation getDonationOnlyWith(Take take) {
        return DonationMother.getValidDonationFor(null, take);
    }

}

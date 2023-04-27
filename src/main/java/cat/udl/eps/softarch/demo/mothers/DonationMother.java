package cat.udl.eps.softarch.demo.mothers;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Donor;
import cat.udl.eps.softarch.demo.domain.Take;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class DonationMother {

    public static Donation getValidDonationFor(@Nullable Donor donor, @Nullable Take take) {
        var donation = new Donation();
        donation.setDonor(donor);
        donation.setTakenBy(take);
        donation.setAmount(1);
        donation.setWeight(BigDecimal.ONE);
        donation.setLocation("Barcelona");
        donation.setDate(ZonedDateTime.now());
        return donation;
    }

}
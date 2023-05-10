package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.exceptions.UserNotDonor;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class DonationEventHandler {
    final Logger logger = LoggerFactory.getLogger(User.class);
    final UserRepository userRepository;
    final DonationRepository donationRepository;

    public DonationEventHandler(UserRepository userRepository, DonationRepository donationRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
    }

    @HandleBeforeCreate
    public void handleDonationPreCreate(Donation donation) {
        logger.info("Before creating: {}", donation.toString());
        if (donation.getDonor() == null) throw new UserNotDonor();
    }
}

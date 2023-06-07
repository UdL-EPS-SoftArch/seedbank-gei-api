package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.exceptions.UserNotDonor;
import cat.udl.eps.softarch.demo.repository.DonationRepository;
import cat.udl.eps.softarch.demo.repository.RequestRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class DonationEventHandler {
    final Logger logger = LoggerFactory.getLogger(User.class);
    final UserRepository userRepository;
    final DonationRepository donationRepository;
    final RequestRepository requestRepository;
    final TakeRepository takeRepository;

    public DonationEventHandler(UserRepository userRepository, DonationRepository donationRepository, RequestRepository requestRepository, TakeRepository takeRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.takeRepository = takeRepository;
        this.requestRepository = requestRepository;
    }

    @HandleBeforeCreate
    public void handleDonationPreCreate(Donation donation) {
        logger.info("Before creating: {}", donation.toString());
        Request request = donation.getTarget();
        if (request != null){
            Take take = new Take();
            take.setAmount(request.getAmount());
            take.setWeight(request.getWeight());
            take.setLocation(request.getLocation());
            take.setTakePropagator(request.getPropagator());
            donation.setTakenBy(take);
            takeRepository.save(take);
            request.setFulfilledBy(take);
            requestRepository.save(request);
        }
        if (donation.getDonor() == null) throw new UserNotDonor();
    }
}

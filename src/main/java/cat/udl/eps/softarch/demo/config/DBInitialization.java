package cat.udl.eps.softarch.demo.config;

import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.mothers.*;
import cat.udl.eps.softarch.demo.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;

@Configuration
public class DBInitialization {
    private final UserRepository userRepository;
    private final DonorRepository donorRepository;
    private final TakeRepository takeRepository;
    private final PropagatorRepository propagatorRepository;
    private final DonationRepository donationRepository;
    private final RequestRepository requestRepository;
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:test}") // if profile is not set, use test
    private String activeProfiles;

    public DBInitialization(UserRepository userRepository, DonorRepository donorRepository, TakeRepository takeRepository, PropagatorRepository propagatorRepository, DonationRepository donationRepository, RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.donorRepository = donorRepository;
        this.takeRepository = takeRepository;
        this.propagatorRepository = propagatorRepository;
        this.donationRepository = donationRepository;
        this.requestRepository = requestRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            Propagator propagator = new Propagator();
            if (!userRepository.existsById("propagator")) {
                propagator.setEmail("propagator@sample.app");
                propagator.setUsername("propagator");
                propagator.setPassword(defaultPassword);
                propagator.encodePassword();
                propagatorRepository.save(propagator);
            }

            if (!userRepository.existsById("test")) {
                User user = new User();
                user.setEmail("test@sample.app");
                user.setUsername("test");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }

            if (!userRepository.existsById("donor")) {
                Donor donor = new Donor();
                donor.setEmail("donor@sample.app");
                donor.setUsername("donor");
                donor.setPassword(defaultPassword);
                donor.encodePassword();
                userRepository.save(donor);
            }

            // Testing instances
            if (!userRepository.existsById("admintest")) {
                User user = new Admin();
                user.setEmail("admintest@sample.app");
                user.setUsername("admintest");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }

            createSampleTakes(propagator);


        }
        if (isProfileActive("prod") && !isProfileActive("test")) {
            // Default donor
            var donor = DonorMother.getValidDonorWith("donor1");
            if (!donorRepository.existsById("donor1")) donorRepository.save(donor);
            // Default propagator
            var propagator = PropagatorMother.getValidPropagatorWith("propagator1");
            if (!propagatorRepository.existsById("propagator1")) propagatorRepository.save(propagator);
            // Default take
            var take = TakeMother.getValidTake();
            take.setTakePropagator(propagator);
            takeRepository.save(take);
            // Default donation
            var donation = DonationMother.getValidDonationFor(donor, take);
            donationRepository.save(donation);
            // Default request
            var request = RequestMother.getValidRequestFor(propagator, take);
            requestRepository.save(request);
        }
    }

    private Boolean isProfileActive(String activeProfile) {
        return Arrays.asList(activeProfiles.split(",")).contains(activeProfile);
    }

    private void createSampleTakes(Propagator propagator) {
        Take take = new Take();
        take.setAmount(6);
        take.setWeight(BigDecimal.TEN);
        take.setLocation("Barcelona");
        take.setDate(ZonedDateTime.now());
        take.setTakePropagator(propagator);
        takeRepository.save(take);
        Take take2 = new Take();
        take2.setAmount(60);
        take2.setWeight(BigDecimal.TEN);
        take2.setLocation("Lleida");
        take2.setDate(ZonedDateTime.now());
        take2.setTakePropagator(propagator);
        takeRepository.save(take2);
        Take take3 = new Take();
        take3.setAmount(12);
        take3.setWeight(BigDecimal.TEN);
        take3.setLocation("Tarragona");
        take3.setDate(ZonedDateTime.now());
        takeRepository.save(take3);
    }
}
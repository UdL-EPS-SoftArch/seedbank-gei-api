package cat.udl.eps.softarch.demo.config;
import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.PropagatorRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import cat.udl.eps.softarch.demo.repository.TakeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;

@Configuration
public class DBInitialization {
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;
    private final TakeRepository takeRepository;

    private final PropagatorRepository propagatorRepository;

    public DBInitialization(UserRepository userRepository, TakeRepository takeRepository, PropagatorRepository propagatorRepository) {
        this.userRepository = userRepository;
        this.takeRepository = takeRepository;
        this.propagatorRepository = propagatorRepository;
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
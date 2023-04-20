package cat.udl.eps.softarch.demo.config;
import cat.udl.eps.softarch.demo.domain.Admin;
import cat.udl.eps.softarch.demo.domain.Take;
import cat.udl.eps.softarch.demo.domain.User;
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

    public DBInitialization(UserRepository userRepository, TakeRepository takeRepository) {
        this.userRepository = userRepository;
        this.takeRepository = takeRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        // Default user
        if (!userRepository.existsById("demo")) {
            User user = new User();
            user.setEmail("demo@sample.app");
            user.setUsername("demo");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("test")) {
                User user = new User();
                user.setEmail("test@sample.app");
                user.setUsername("test");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }

            createSampleTakes();


        }

        if (Arrays.asList(activeProfiles.split(",")).contains("admintest")) {
            // Testing instances
            if (!userRepository.existsById("admintest")) {
                User user = new Admin();
                user.setEmail("admintest@sample.app");
                user.setUsername("admintest");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }
            createSampleTakes();

        }
    }

    private void createSampleTakes() {
        Take take = new Take();
        take.setAmount(6);
        take.setWeight(BigDecimal.TEN);
        take.setLocation("Barcelona");
        take.setDate(ZonedDateTime.now());
        takeRepository.save(take);
        Take take2 = new Take();
        take2.setAmount(60);
        take2.setWeight(BigDecimal.TEN);
        take2.setLocation("Lleida");
        take2.setDate(ZonedDateTime.now());
        takeRepository.save(take2);
        Take take3 = new Take();
        take3.setAmount(12);
        take3.setWeight(BigDecimal.TEN);
        take3.setLocation("Tarragona");
        take3.setDate(ZonedDateTime.now());
        takeRepository.save(take3);
    }
}
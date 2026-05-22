package personapp.naim.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import personapp.naim.model.AppUser;
import personapp.naim.model.Person;
import personapp.naim.repository.AppUserRepository;
import personapp.naim.repository.PersonRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PersonRepository personRepository,
                           AppUserRepository appUserRepository,
                           PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (personRepository.count() == 0) {
            personRepository.save(new Person("Alice", "Johnson", "alice@example.com", 28));
            personRepository.save(new Person("Bob", "Murphy", "bob@example.com", 34));
        }

        if (appUserRepository.count() == 0) {
            appUserRepository.save(new AppUser(
                    "admin",
                    passwordEncoder.encode("password"),
                    "ROLE_USER",
                    "admin@example.com"
            ));
        }
    }
}

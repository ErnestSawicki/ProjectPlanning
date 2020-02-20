package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.initializers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;

import java.util.*;

@Slf4j
public class UserInitializer {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.projectRepository = projectRepository;
    }



    public void initializeUserSampleData() {

        log.info("UserInitializer: Starting to initialize user sample data ....");
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setActive(true);
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String username = lastName + "." + firstName.charAt(0);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(fakeValuesService.bothify(username + "@wabco-auto.com"));
            user.setPassword(passwordEncoder.encode("pass"));
            user.setRole("USER");

            userRepository.save(user);
        }
        log.info("UserInitializer: ...finished with data initialization");
    }
}

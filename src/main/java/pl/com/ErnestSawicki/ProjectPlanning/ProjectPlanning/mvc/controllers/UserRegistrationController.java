package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getRegisterPage() {
        return "/WEB-INF/views/user-register.jsp";
    }

    @PostMapping
    public String registerUser(@RequestParam String userName,
                               @RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String password) {
        System.out.println(userName + firstName + lastName + email + password);
        User user = new User();
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        System.out.println(user.toString());
        userRepository.save(user);

        return "redirect:/";
    }
}

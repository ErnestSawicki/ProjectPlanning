package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import com.fasterxml.jackson.databind.BeanProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.config.passwordConfig.PasswordConstraintValidator;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.UserDTORegistration;

import javax.validation.Valid;

@Controller
@Slf4j
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
        return "user-register";
    }

    @PostMapping
    public String registerUser(@Valid UserDTORegistration userDTORegistration, BindingResult result) {
        if (result.hasErrors()){
            return "redirect:/register";
        }

        log.trace("Form data -> userDTORegistration: {}", userDTORegistration.toString());
        User registeredUser = new User();
        BeanUtils.copyProperties(userDTORegistration, registeredUser);
        registeredUser.setPassword(passwordEncoder.encode(registeredUser.getPassword()));
        registeredUser.setRole("USER");
        registeredUser.setActive(true);
        log.trace("Created user -> {}", registeredUser.toString());
        userRepository.save(registeredUser);
        return "redirect:/";
    }
}

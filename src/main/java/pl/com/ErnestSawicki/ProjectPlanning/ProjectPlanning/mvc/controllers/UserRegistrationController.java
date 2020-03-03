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
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.UserService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "user-register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDTORegistration userDTORegistration, BindingResult result) {
        if (result.hasErrors()){

            return "redirect:/register";
        }
        userService.registerUser(userDTORegistration);
        return "redirect:/";
    }
}

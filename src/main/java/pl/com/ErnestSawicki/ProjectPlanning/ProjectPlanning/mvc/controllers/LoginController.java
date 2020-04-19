package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.ConfirmationToken;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ConfirmationTokenRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.UserDTOPasswordReset;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.LoginService;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations.EmailSenderServiceDefault;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginService loginService;

    @Autowired
    public LoginController(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository, PasswordEncoder passwordEncoder, LoginService loginService) {
        this.userRepository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginService = loginService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/forgotPassword")
    public String getForgotPasswordPage() {
        return "user-forgotPassword";
    }

    @PostMapping(value = "/forgotPassword")
    public String userForgotPassword(@RequestParam String email) {
        loginService.sendForgotPasswordEmail(email);
        return "home-page";
    }

    @GetMapping(value = "/confirm-reset")
    public String getPasswordResetPage(@RequestParam String token, Model model) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        User user = confirmationToken.getUser();
        model.addAttribute("user", user);
        model.addAttribute("confirmationToken", confirmationToken.isTokenUsed());
        confirmationTokenRepository.save(confirmationToken);
        confirmationToken.setTokenUsed(true);
        return "user-passwordReset";
    }

    @PostMapping(value = "/confirm-reset")
    public String resetUserPassword(@Valid UserDTOPasswordReset userDTOPasswordReset) {
        loginService.resetUserPassword(userDTOPasswordReset);
        return "/login";
    }
}

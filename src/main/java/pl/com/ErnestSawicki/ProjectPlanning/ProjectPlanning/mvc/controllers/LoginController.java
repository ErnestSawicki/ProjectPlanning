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
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations.EmailSenderServiceDefault;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController  {

    private final UserRepository userRepository;
    private final EmailSenderServiceDefault emailSenderService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserRepository userRepository, EmailSenderServiceDefault emailSenderService, ConfirmationTokenRepository confirmationTokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @GetMapping(value = "/forgotPassword")
    public String getForgotPasswordPage(){
        return "user-forgotPassword";
    }

    @PostMapping(value = "/forgotPassword")
    public String userForgotPassword(@RequestParam String email){
        User user = userRepository.findUserByEmail(email);
        if(user != null){

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("ErnestSawicki94@gmail.com");
            mailMessage.setSubject("FTROT - password reset");
            mailMessage.setFrom("FTROT@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:8080/login/confirm-reset?token="+ confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
        }
        return "home-page";
    }

    @GetMapping(value = "/confirm-reset")
    public String getPasswordResetPage(@RequestParam String token, Model model){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        if (!confirmationToken.isTokenUsed()){
            User user = confirmationToken.getUser();
            model.addAttribute("user", user);
            model.addAttribute("confirmationToken", token);
            confirmationToken.setTokenUsed(true);
            confirmationTokenRepository.save(confirmationToken);
        } else {
            return  "redirect:/";
        }
        return "user-passwordReset";
    }

    @PostMapping(value = "/confirm-reset")
    public String resetUserPassword(@Valid UserDTOPasswordReset userDTOPasswordReset){
        User userByUsername = userRepository.findUserByUsername(userDTOPasswordReset.getUsername()).get(0);
        if (userDTOPasswordReset.getNewPassword().matches(userDTOPasswordReset.getConfirmPassword())){
            userByUsername.setPassword(passwordEncoder.encode(userDTOPasswordReset.getNewPassword()));
            userRepository.save(userByUsername);
        }
        return "/login";
    }
}

package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations.EmailSenderServiceDefault;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController  {

    private final UserRepository userRepository;
    private final EmailSenderServiceDefault emailSenderService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public LoginController(UserRepository userRepository, EmailSenderServiceDefault emailSenderService, ConfirmationTokenRepository confirmationTokenRepository) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenRepository = confirmationTokenRepository;
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
        User user = confirmationToken.getUser();
        model.addAttribute("user", user);
        model.addAttribute("confirmationToken", token);
        return "user-passwordReset";
    }

    @PostMapping(value = "/confirm-reset")
    public String resetUserPassword(@RequestParam String username, @RequestParam String newPassword, @RequestParam String confirmPassword){
        User userByUsername = userRepository.findUserByUsername(username).get(0);
        if (newPassword.matches(confirmPassword)){
            userByUsername.setPassword(newPassword);
            userRepository.save(userByUsername);
        }
        return "/login";
    }
}

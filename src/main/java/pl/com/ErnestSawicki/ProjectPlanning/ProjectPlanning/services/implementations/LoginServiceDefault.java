package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.ConfirmationToken;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ConfirmationTokenRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.user.UserDTOPasswordReset;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.EmailSenderService;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.LoginService;

@Service
public class LoginServiceDefault implements LoginService {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceDefault(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void sendForgotPasswordEmail(String userEmail){
        User user = userRepository.findUserByEmail(userEmail);
        if (user != null) {
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("ErnestSawicki94@gmail.com");
            mailMessage.setSubject("FTROT - password reset");
            mailMessage.setFrom("FTROT@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:8080/login/confirm-reset?token=" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
        }
    }

    @Override
    public void resetUserPassword(UserDTOPasswordReset userDTOPasswordReset) {
        User userByUsername = userRepository.findUserByUsername(userDTOPasswordReset.getUsername()).get(0);
        if (userDTOPasswordReset.getNewPassword().matches(userDTOPasswordReset.getConfirmPassword())) {
            userByUsername.setPassword(passwordEncoder.encode(userDTOPasswordReset.getNewPassword()));
            userRepository.save(userByUsername);
        }
    }
}

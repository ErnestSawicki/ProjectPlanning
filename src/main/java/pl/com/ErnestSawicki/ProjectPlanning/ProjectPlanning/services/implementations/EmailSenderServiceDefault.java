package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceDefault {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderServiceDefault(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }

}

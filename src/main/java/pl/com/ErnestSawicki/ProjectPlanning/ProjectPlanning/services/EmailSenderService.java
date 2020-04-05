package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {

    void sendEmail(SimpleMailMessage email);
}

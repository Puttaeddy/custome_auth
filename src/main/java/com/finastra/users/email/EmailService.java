package com.finastra.users.email;


import com.finastra.users.consts.MailConstants;
import com.finastra.users.dto.UserDto;
import com.microsoft.graph.models.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String emailSender;

    private UserDto theUsers;
    private User user;

    @Autowired
    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmailWithHtmlTemplate(UserDto theUsers,User user, String templateName) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
        Context context = new Context();
        context.setVariable("displayName",user.displayName);
        context.setVariable("username",user.userPrincipalName);
        context.setVariable("password",user.passwordProfile.password);
        try {
            messageHelper.setFrom(emailSender, MailConstants.SENDER_NAME);
            messageHelper.setTo(theUsers.getEmail());
            messageHelper.setSubject(MailConstants.SUBJECT);
            String mailContent = templateEngine.process(templateName,context);
            messageHelper.setText(mailContent, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            // Handle exception
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
package com.springbootprojects.booknetworkapi.email;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Async
    public void sendEmail(
        String to,
        String username,
        EmailTemplateName emailTemplateName,
        String confirmationUrl,
        String activationCode,
        String subject
    ) throws MessagingException {
        
        String templateName;

        if(emailTemplateName == null) {
            templateName = "confirm_email";
        } else {
            templateName = emailTemplateName.name();
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
            mimeMessage,
            MimeMessageHelper.MULTIPART_MODE_MIXED,
            StandardCharsets.UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmation_url", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context context = new Context();
        context.setVariables(properties);

        mimeMessageHelper.setFrom("contact@cronero.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        String template = springTemplateEngine.process(templateName, context);

        mimeMessageHelper.setText(template, true);

        javaMailSender.send(mimeMessage);
    }
}

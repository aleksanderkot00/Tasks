package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(Mail mail, boolean isCountingMail) {

        LOGGER.info("Preparing mail to send");

        try{
            javaMailSender.send(createMimeMessage(mail,isCountingMail));
            LOGGER.info("Mail sent properly");
        } catch (MailException e) {
            LOGGER.error("Failed to send mail: ", e.getMessage(), e);
        }
    }

    public MimeMessagePreparator createMimeMessage(final Mail mail, final boolean isCountingMail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            if (isCountingMail) {
                messageHelper.setText(mailCreatorService.buildCountingTasksEmail(mail.getMessage()), true);
            } else {
                messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
            }
        };
    }
}
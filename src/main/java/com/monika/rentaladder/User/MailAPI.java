package com.monika.rentaladder.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;

public class MailAPI {

    private MailService mailService;

    @Autowired
    public MailAPI(MailService mailService) {
        this.mailService = mailService;
    }

    public String sendMail() throws MessagingException {
        mailService.sendMail("mksadlowska@gmail.com",
                "First mail from RentAll",
                "<b>Udało się!</b><br>:P", true);
        return "E-mail has been sent.";
    }
}

package com.monika.rentaladder.User.MailSender;

import com.monika.rentaladder.User.UserEntity;
import com.monika.rentaladder.User.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender javaMailSender;
    private UserFacade userFacade;
    private final String baseURL = "";//ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

    @Autowired
    public MailService(JavaMailSender javaMailSender, UserFacade userFacade) {
        this.javaMailSender = javaMailSender;
        this.userFacade = userFacade;
    }

    @EventListener
    @Async("sendingEmailTaskExecutor")
    public void eventListener(MailMessage mailMessage) {
        MailMessage.Type type = mailMessage.getMassageType();
        switch (type) {
            case CONTACT:
                sendOwnerDataMail(mailMessage.getSendToName(), mailMessage.getItemName(),
                        mailMessage.getItemOwnerName(), mailMessage.getRentalId());
                break;
            case REQUEST:
                sendRequestMail(mailMessage.getSendToName(), mailMessage.getItemName(),
                        mailMessage.getItemOwnerName(), mailMessage.getRentalId());
                break;
        }
    }

    public void sendRequestMail(String owner, String name, String user, Long id) {
        UserEntity ownerEntity = userFacade.getUserByName(owner);
        String htmlContent = "User " + user + " wants to rent your " + name + ".<br>" +
                "Do you agree?<br><br>" +
                "<a href=\"" + baseURL + "\"><button>OK</button></a>   " +
                "<a href=\"" + baseURL + "\"><button>NO</button></a>";
        try {
            sendMail(ownerEntity.geteMail(), "Rental Request from " + user, htmlContent, true);
        } catch (MessagingException e) {
        }
    }

    public void sendOwnerDataMail(String owner, String name, String user, Long id) {
        UserEntity userEntity = userFacade.getUserByName(user);
        UserEntity ownerEntity = userFacade.getUserByName(owner);
        String htmlContent = "This is a message regarding your rental request for <b>" + name + ".</b><br><br>"+
                "Please contact User " + owner + " to arrange its pick-up.<br>" +
                "Phone number: " + ownerEntity.getPhoneNumber();
        try {
            sendMail(userEntity.geteMail(), "Item owner contact details", htmlContent, true);
        } catch (MessagingException e) {
        }
    }

    private void sendMail(String to,
                         String subject,
                         String text,
                         boolean isHtmlContent) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String finalContent = "<img src='cid:logo_mail'><br>" + text + "<br><br>" +
                "Thank you for using <b>RentAll</b>, the best place to find anything you need.<br><br>" +
                "<i>This message has been sent automatically. Please do not answer.</i>";
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(finalContent, isHtmlContent);
        mimeMessageHelper.addInline("logo_mail", new ClassPathResource("static/images/logo.png"));
        javaMailSender.send(mimeMessage);
    }

}

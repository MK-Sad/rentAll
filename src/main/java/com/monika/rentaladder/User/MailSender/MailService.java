package com.monika.rentaladder.User.MailSender;

import com.monika.rentaladder.User.UserDTOs.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    private final String baseURL = "http://localhost:8080/";

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendRequestMail(UserEntity owner, UserEntity user, String itemName, Long rentalId) {
        String htmlContent = "User " + user.getName() + " (" + user.getPoints() + " points) +" +
                "wants to rent your " + itemName + ".<br>" +
                "Do you agree?<br><br>" +
                "<a href=\"" + baseURL + "/confirmRental/" + rentalId + "\"><button>OK</button></a>   " +
                "<a href=\"" + baseURL + "/denyRental/" + rentalId + "\"><button>NO</button></a>";
        try {
            sendMail(owner.geteMail(), "Rental Request from " + user.getName(), htmlContent);
        } catch (MessagingException e) {
        }
    }

    public void sendOwnerDataMail(UserEntity owner, UserEntity user, String itemName, Long rentalId) {
        String htmlContent = "This is a message regarding your rental request for <b>" + itemName +
                ".</b><br><br>"+
                "Please contact User " + owner + " to arrange its pick-up.<br>" +
                "Phone number: " + owner.getPhoneNumber();
        try {
            sendMail(user.geteMail(), "Item owner contact details", htmlContent);
        } catch (MessagingException e) {
        }
    }

    public void sendSorryMail(UserEntity owner, UserEntity user, String itemName, Long rentalId) {
        String htmlContent = "This is a message regarding your rental request for <b>" + itemName +
                ".</b><br><br>"+
                "Sorry ;( You have to ask somebody else...";
        try {
            sendMail(user.geteMail(), "Item owner contact details", htmlContent);
        } catch (MessagingException e) {
        }
    }

    private void sendMail(String to,
                         String subject,
                         String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String finalContent = "<img src='cid:logo_mail'><br>" + text + "<br><br>" +
                "Thank you for using <b>RentAll</b>, the best place to find anything you need.<br><br>" +
                "<i>This message has been sent automatically. Please do not answer.</i>";
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(finalContent, true);
        mimeMessageHelper.addInline("logo_mail", new ClassPathResource("static/images/logo.png"));
        javaMailSender.send(mimeMessage);
    }

}

package com.example.ceertifications.service;

import com.example.ceertifications.dto.enums.EmailSubject;
import com.example.ceertifications.security.SmtpAuthenticator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {




    public void sendEmail(String toEmail, String validationCode, EmailSubject emailSubject) throws MessagingException, UnsupportedEncodingException {
        try
        {
            //String smtpHostServer = "smtp.gmail.com";
            String smtpHostServer = "mail.sipcca.com";



            String body = emailSubject.subject + validationCode;
            String subject = emailSubject.body;

            //final String fromEmail = "ngadene11@gmail.com"; //requires valid gmail id
            //final String password = "Rivaldo@10"; // correct password for gmail id

            final String fromEmail = "info@sipcca.com";
            final String password = "dEM[wB]z;WyA";

            Properties props = new Properties();
            props.put("mail.smtp.host", smtpHostServer); //SMTP Host
            //props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.port", "465"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            props.put("mail.smtp.ssl.enable", "true");

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };

            SmtpAuthenticator authentication = new SmtpAuthenticator();
            Session session = Session.getInstance(props, auth);


            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse(fromEmail, false));


            msg.setSubject(subject, "UTF-8");



            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String generateRandomAlphanumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }


}

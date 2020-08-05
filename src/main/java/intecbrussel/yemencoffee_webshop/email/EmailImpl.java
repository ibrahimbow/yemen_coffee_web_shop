package intecbrussel.yemencoffee_webshop.email;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Repository
public class EmailImpl implements SendEmail{



    private EmailInfo sendEmail = new EmailInfo();


    public void sendEmail(){
    Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.live.com");
		properties.put("mail.smtp.port", "587");

    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(sendEmail.getUsername(),sendEmail.getPassword());
        }
    });

    //Start our mail message
    MimeMessage msg = new MimeMessage(session);
		try {
        msg.setFrom(new InternetAddress(sendEmail.getFromEmail()));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendEmail.getToEmail()));
        msg.setSubject("Subject Line");

        Multipart emailContent = new MimeMultipart();

        //Text body part
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText("My multipart text");
            // Here we can add the text of message
            // and attached the product with price and confirmation of payment
            // Ex: Dear getCustomerName();
            // message
            // product that you bought
            // fetch from database - table orders
            // method to implement the orders of the customer
            // getCustomerOrder();


        //Attachment body part.
//        MimeBodyPart pdfAttachment = new MimeBodyPart();
//        pdfAttachment.attachFile("add attachment like logo of the company");

        //Attach body parts
        emailContent.addBodyPart(textBodyPart);
//        emailContent.addBodyPart(pdfAttachment);

        //Attach multipart to message
        msg.setContent(emailContent);

        Transport.send(msg);
        System.out.println("Sent message");
    } catch (MessagingException e) {
        e.printStackTrace();
    }
		// here we can add the catch of io
    }
}

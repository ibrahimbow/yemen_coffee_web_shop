package intecbrussel.yemencoffee_webshop.repositories.implementation;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.repositories.SendEmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.Entity;

@Repository
public class EmailImpl implements SendEmailRepo {



    @Override
    public void sendEmail(SendEmailInfo sendEmailInfo) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.live.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendEmailInfo.getUsername(),sendEmailInfo.getPassword());
            }
        });

        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(sendEmailInfo.getFromEmail()));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendEmailInfo.getToEmail()));
            msg.setSubject("Confirmation of you Order ");

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();

//            textBodyPart.setText("<html><body>Here is a cat picture! <img src='cid:id101'/><body></html>");
//
            textBodyPart.setText(" Dear " + sendEmailInfo.getCustomer().getFull_name() + "\n\n" + "" +
                    "We have received your order\n\n" +"" +
                    " here is your orders");

            // TODO: 8/15/2020
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

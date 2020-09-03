package intecbrussel.yemencoffee_webshop.repositories.implementation;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Order;
import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.repositories.SendEmailDao;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.OptionalInt;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Repository
public class EmailImpl implements SendEmailDao {



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

            //get formatted date and time
            String DATE_FORMATTER = "yyyy-MM-dd ' at ' HH:mm:ss";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

            // get only the first name
            String fullName = sendEmailInfo.getCustomer().getFull_name();
            int space = fullName.indexOf(' ');
            String firstName = sendEmailInfo.getCustomer().getFull_name();
            if(space !=-1) {
                firstName = fullName.substring(0, space);
            }

            String orderNumber = sendEmailInfo.getCustomer().getOrderList().stream().mapToInt(Order::getOrder_number).findAny().toString();

            textBodyPart.setText("Dear " + firstName +
                    "\n\n  "+ "Thank you very much for your order. The order number is: " +
                    orderNumber
                     + " dated " + String.format(sendEmailInfo.getCustomer().getOrderList().stream()
                    .map(Order::getOrder_date).toString(),formatter ) +
                    "has ben sent to the following address \n\n" +
                    "Address:\n" +
                    sendEmailInfo.getCustomer().getFull_name() +
                    "\n" +
                    sendEmailInfo.getCustomer().getAddress() +
                    "\n" +
                    sendEmailInfo.getCustomer().getZipcode() + "  " + sendEmailInfo.getCustomer().getCity()+
                    "\n" +
                    sendEmailInfo.getCustomer().getCountry() + " \n\n" +
                    "Thank you for your purchase and enjoy Drink the best Quality of Yemen Coffee.\n\n\n");

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





    // Receive Question by email from the user


}

package intecbrussel.yemencoffee_webshop.repositories.implementation;

import intecbrussel.yemencoffee_webshop.model.Order;
import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.repositories.SendEmailDao;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            String DATE_FORMATTER = "yyyy-MM-dd ' at ' HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);


            // get only the first name
            String fullName = sendEmailInfo.getCustomer().getFull_name();
            int space = fullName.indexOf(' ');
            String firstName = sendEmailInfo.getCustomer().getFull_name();
            if(space !=-1) {
                firstName = fullName.substring(0, space);
            }
            // we use .getAsInt(); to convert OptionalInt[] to int
            int orderNumber = sendEmailInfo.getCustomer().getOrderList()
                    .stream()
                    .mapToInt(Order::getOrder_number)
                    .findAny()
                    .getAsInt();

            textBodyPart.setText("Dear " + firstName +
                    "\n\n"+ "Thank you very much for your order. The order number is: " +
                    orderNumber
                    + " dated " + LocalDateTime.now().format(formatter) +
                    "  has ben sent to the following address \n\n" +
                    "Address:\n" +
                    sendEmailInfo.getCustomer().getFull_name() +
                    "\n" +
                    sendEmailInfo.getCustomer().getAddress() +
                    "\n" +
                    sendEmailInfo.getCustomer().getZipcode() + "  " + sendEmailInfo.getCustomer().getCity()+
                    "\n" +
                    sendEmailInfo.getCustomer().getCountry() + " \n\n" +
                    "Thank you for your purchase and enjoy Drink the best Quality of Yemen Coffee.\n\n\n");

            //Attachment body part.
//        MimeBodyPart pdfAttachment = new MimeBodyPart();
//        pdfAttachment.attachFile("add attachment like logo of the company");

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
//            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // here we can add the catch of io
    }

    @Override
    public void sendPassword(SendEmailInfo sendEmailInfo) {

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
            msg.setSubject("new password");

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();

            // get only the first name
            String fullName = sendEmailInfo.getCustomer().getFull_name();
            int space = fullName.indexOf(' ');
            String firstName = sendEmailInfo.getCustomer().getFull_name();
            if(space !=-1) {
                firstName = fullName.substring(0, space);
            }
            textBodyPart.setText("Dear " + firstName +
                    "\n\n"+ "Thank you very much for using our web shop\n\n"+
                    "Your new Password is:\n" +
                    sendEmailInfo.getCustomer().getPassword() +"\n\n"
                    +"now you can login with your email this password \n then you can change it in your customer settings \n\n\n");

            emailContent.addBodyPart(textBodyPart);

            //Attach multipart to message
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

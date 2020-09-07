package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.repositories.SendEmailDao;
import intecbrussel.yemencoffee_webshop.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    private SendEmailDao sendEmailDao;


    @Autowired
    public void setSendEmailDao(SendEmailDao sendEmailDao) {
        this.sendEmailDao = sendEmailDao;
    }

    @Override
    public void SendEmailTo(SendEmailInfo sendEmailInfo) {
        this.sendEmailDao.sendEmail(sendEmailInfo);
    }

    @Override
    public void sendPassword(SendEmailInfo sendEmailInfo) {
        this.sendEmailDao.sendPassword(sendEmailInfo);
    }


}

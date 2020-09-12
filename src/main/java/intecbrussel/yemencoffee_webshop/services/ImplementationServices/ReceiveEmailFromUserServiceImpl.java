package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.ReceiveEmailFromUser;
import intecbrussel.yemencoffee_webshop.repositories.ReceiveEmailFromUserDao;
import intecbrussel.yemencoffee_webshop.services.ReceiveEmailFromUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveEmailFromUserServiceImpl implements ReceiveEmailFromUserService {

    private ReceiveEmailFromUserDao receiveEmailFromUserDao;

    @Autowired
    public void setReceiveEmailFromUserDao(ReceiveEmailFromUserDao receiveEmailFromUserDao) {
        this.receiveEmailFromUserDao = receiveEmailFromUserDao;
    }

    @Override
    public void receiveEmail(ReceiveEmailFromUser receiveEmailFromUser) {
        this.receiveEmailFromUserDao.receiveEmail(receiveEmailFromUser);
    }
}

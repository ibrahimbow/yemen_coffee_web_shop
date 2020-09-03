package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.ReceiveEmailFromUser;
import intecbrussel.yemencoffee_webshop.repositories.ReceiveEmailFromUserRepo;
import intecbrussel.yemencoffee_webshop.services.ReceiveEmailFromUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveEmailFromUserServiceImpl implements ReceiveEmailFromUserService {

    private ReceiveEmailFromUserRepo receiveEmailFromUserRepo;

    @Autowired
    public void setReceiveEmailFromUserRepo(ReceiveEmailFromUserRepo receiveEmailFromUserRepo) {
        this.receiveEmailFromUserRepo = receiveEmailFromUserRepo;
    }

    @Override
    public void receiveEmail(ReceiveEmailFromUser receiveEmailFromUser) {
        this.receiveEmailFromUserRepo.receiveEmail(receiveEmailFromUser);
    }
}

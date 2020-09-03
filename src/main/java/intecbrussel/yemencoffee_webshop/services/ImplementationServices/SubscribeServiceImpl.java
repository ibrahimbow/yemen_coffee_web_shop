package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.Subscribe;
import intecbrussel.yemencoffee_webshop.repositories.SubscribeRepo;
import intecbrussel.yemencoffee_webshop.services.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeServiceImpl implements SubscribeService {

    private SubscribeRepo subscribeRepo;

    @Autowired
    public void setSubscribeRepo(SubscribeRepo subscribeRepo) {
        this.subscribeRepo = subscribeRepo;
    }

    @Override
    public void addNewSubscriber(Subscribe subscribe) {
        subscribeRepo.save(subscribe);
    }
}

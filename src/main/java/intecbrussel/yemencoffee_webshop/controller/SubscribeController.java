package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.Subscribe;
import intecbrussel.yemencoffee_webshop.services.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubscribeController {


    private SubscribeService subscribeService;

    @Autowired
    public void setSubscribeService(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }



    @PostMapping("/subscribe")
    public String addNewSubscriber(@ModelAttribute(name = "subscriber")Subscribe subscribe){
        subscribeService.addNewSubscriber(subscribe);
        return "redirect:/";
    }
}

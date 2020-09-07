package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.Subscribe;
import intecbrussel.yemencoffee_webshop.services.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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


    @PostMapping(value = "/checkEmailSubs", produces = "text/html")
    @ResponseBody
    public String checkEmailSubscribe(
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response) {
        String email = request.getParameter("param2");
        try {
            if(subscribeService.checkEmailSubscribe(email)!=null) {
                return  "emailIsExist";
            } else {
                return  "";
            }
        } catch (Exception b) {
            b.getMessage();
        }
        return  "emailIsExist";
    }
}

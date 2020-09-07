package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.ReceiveEmailFromUser;
import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.services.ReceiveEmailFromUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

@Controller
public class ReceiveUserQuestionsController {

    private ReceiveEmailFromUserService receiveEmailFromUserImpl;

    @Autowired
    public void setReceiveEmailFromUserImpl(ReceiveEmailFromUserService receiveEmailFromUserImpl) {
        this.receiveEmailFromUserImpl = receiveEmailFromUserImpl;
    }

    @PostMapping("/send_question")
    public String sendUserQuestion(@ModelAttribute("user_question") ReceiveEmailFromUser userWhoHasQuestion,
                                   HttpSession session){

        SendEmailInfo sendEmailInfo = new SendEmailInfo();
        ReceiveEmailFromUser receiveEmailFromUser = new ReceiveEmailFromUser();
        receiveEmailFromUser.setSendEmailInfo(sendEmailInfo);
        receiveEmailFromUser.setUsernameWhoHasQuestion(userWhoHasQuestion.getUsernameWhoHasQuestion());
        receiveEmailFromUser.setEmailWhoHasQuestion(userWhoHasQuestion.getEmailWhoHasQuestion());
        receiveEmailFromUser.setSubjectWhoHasQuestion(userWhoHasQuestion.getSubjectWhoHasQuestion());
        receiveEmailFromUser.setMessageWhoHasQuestion(userWhoHasQuestion.getMessageWhoHasQuestion());

        this.receiveEmailFromUserImpl.receiveEmail(receiveEmailFromUser);
        session.setAttribute("Message_is_Sent","Message is Sent");
        return "redirect:/";

    }
}

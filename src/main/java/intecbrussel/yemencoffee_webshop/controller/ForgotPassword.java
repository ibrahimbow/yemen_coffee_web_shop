package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.CustomerServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.SendEmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class ForgotPassword {

    private CustomerServiceImpl customerService;
    private SendEmailServiceImpl sendEmailServiceImpl;

    @Autowired
    public void setCustomerService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setSendEmailServiceImpl(SendEmailServiceImpl sendEmailServiceImpl) {
        this.sendEmailServiceImpl = sendEmailServiceImpl;
    }



    @GetMapping("/forgot_password")
    public String forgotYourPassword(Model model){
        model.addAttribute("password_customer",new Customer());
        return "contents/forgot_password";
    }


        @GetMapping("/get_user_id_via_email")
        public String getUserIdViaEmail( @ModelAttribute("password_customer") Customer customer,
                RedirectAttributes redirectAttributes, HttpSession session){
            long id = 0;
            if(customerService.checkEmail(customer.getEmail())!=null){
                redirectAttributes.addAttribute("id", customerService.checkEmail(customer.getEmail()).getId());
            }else{
                session.setAttribute("invalid_email_error", true);
                return "redirect:/forgot_password";
            }
            return "redirect:/send_password/{id}";
        }


    @GetMapping("/send_password/{id}")
    public String sendNewPassword(@PathVariable( value = "id") Long id){
        SendEmailInfo sendPassword = new SendEmailInfo();
        Customer customer = customerService.getCustomerById(id);
        customerService.getCustomerById(id).setPassword("T$s8823kj!sd2A@#Q3");
        customerService.addNewCustomer(customer);
        sendPassword.setToEmail(customerService.getCustomerById(id).getEmail());
        sendPassword.setCustomer(customerService.getCustomerById(id));


        sendEmailServiceImpl.sendPassword(sendPassword);

        return "contents/successful";
    }

}


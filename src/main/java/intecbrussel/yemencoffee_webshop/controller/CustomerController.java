package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.model.Order;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {



    private CustomerServiceImpl customerService;

    @Autowired
    public void setCustomerService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    //==========================================================
    //===============Customer_Administrator=====================
    //==========================================================

    //-----------------LOGIN ---------------------
    // to get login form page

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String ViewLogin(Model model){
        Customer customer = new Customer();
        model.addAttribute("register_new_customer",customer);

        return "contents/login_register";
    }

    @RequestMapping(value = "/login_form", method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute(name = "user") Customer customer1, Model model){
        String email = customer1.getEmail();
        String password = customer1.getPassword();

        if(customerService.checkLogin(email,password)!=null) {
            // this way to get the name of the user who logged in
            String name = customerService.checkLogin(email,password).getFull_name();
            session.setAttribute("welcome_user_name",name);
            session.setAttribute("welcome_user",customerService.checkLogin(email,password));

            return "redirect:/";
        }
        session.setAttribute("invalidError", true);
        return "contents/login_register";
    }

//-----------------REGISTRATION---------------------
//
//    @GetMapping("/register_form")
//    public String getRegisterForm(Model model){
//        Customer customer = new Customer();
//        model.addAttribute("register_new_customer",customer);
//
//        return "contents/login_register";
//    }

    @PostMapping("/register_form")
    public String registerNewCustomer(HttpSession session,
                                      @ModelAttribute("register_new_customer") Customer customer, Model model){
        customerService.addNewCustomer(customer);

        String name = customerService.getCustomerById(customer.getId()).getFull_name();
//        session.setAttribute("welcome_user_name",customerService.getCustomerById(customer.getId()));

        session.setAttribute("welcome_user",customer);
//        session.setAttribute("welcome_customer",customer);
        return "redirect:/";
    }

//=============================================================

// customer info

    @GetMapping("/customer_cms")
    public String viewCustomer_cms(@ModelAttribute(name = "user") Customer customer1,
                                   Model model){
        model.addAttribute("welcome_user",customer1);
        return "contents/customer";
    }

    @GetMapping("/show_customer_info/{id}")
    public String showCustomerInfo(@PathVariable (value = "id") Long id, Model model){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customers_info",customer);
        return "contents/customer_info";
    }

    @PostMapping("/update_customer_info")
    public String saveCustomer(@ModelAttribute("customers_info") Customer customer,
                               HttpSession session){
        customerService.addNewCustomer(customer);

        String name = customerService.getCustomerById(customer.getId()).getFull_name();
        session.setAttribute("welcome_user_name",name);
        return "redirect:/customer_cms";
    }



    @GetMapping("/show_customer_orders/{id}")
    public String showCustomerOrders(@PathVariable (value = "id") Long id, Model model){
        List<Order> orderList = customerService.getCustomerById(id).getOrderList();
        List<CartItems> itemsList = new ArrayList<>();

        for (int i = 0; i < orderList.get(i).getCart().getCartItemsList().size() ; i++) {
            itemsList= orderList.get(i).getCart().getCartItemsList();
        }

        model.addAttribute("customer_orders",orderList);
        model.addAttribute("items_list",itemsList);
        return "contents/customer_order";
    }


}

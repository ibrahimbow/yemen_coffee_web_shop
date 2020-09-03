package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.model.Order;
import intecbrussel.yemencoffee_webshop.services.CartItemsService;
import intecbrussel.yemencoffee_webshop.services.CartService;
import intecbrussel.yemencoffee_webshop.services.CustomerOrderService;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.*;

@Controller
public class CustomerController {



    private CustomerServiceImpl customerService;
    private CartService cartService;
    private CartItemsService cartItemsService;
    private CustomerOrderService customerOrderService;

    @Autowired
    public void setCustomerOrderService(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @Autowired
    public void setCustomerService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setCartItemsService(CartItemsService cartItemsService) {
        this.cartItemsService = cartItemsService;
    }

    //==========================================================
    //===============Customer_Administrator=====================
    //==========================================================

    //-----------------LOGIN ---------------------
    // to get login form page

    @GetMapping(value = "/account")
    public String View_Login_Registration(Model model){
        model.addAttribute("login_register_customer",new Customer());

        return "contents/login_register";
    }



    @GetMapping(value = "/check_login_form")
    public String checkEmailLogin(HttpSession session, @ModelAttribute("login_register_customer") Customer customer1,Model model) {

        String email = customer1.getEmail();
        String password = customer1.getPassword();

        if(customerService.checkLogin(email,password)==null) {
            session.setAttribute("invalid_user_error", true);
            return "redirect:/account";
        }
        session.setAttribute("welcome_user",customerService.checkLogin(email,password));
        return "redirect:/";
    }

//-----------------REGISTRATION---------------------
//
//    @PostMapping("/check_email_register")
//    public String checkEmailRegisterCustomer(@ModelAttribute("login_register_customer") Customer customer,
//                                             HttpSession session,
//                                             Model model,
//                                             final RedirectAttributes redirectAttributes){
//            customerService.addNewCustomer(customer);
//            session.setAttribute("welcome_user", customer);
//            return "redirect:/";
//
//    }






    @PostMapping(value = "/check_email_register", produces = "text/html")
    @ResponseBody
//    @PostMapping("/check_email_register")
    public String checkEmailRegisterCustomer(
                                             HttpServletRequest request,
                                             HttpServletResponse response,
                                             HttpSession session
                                             ) throws IOException {

        String userName = request.getParameter("myuserReg");
        String email = request.getParameter("email");
        String pwd = request.getParameter("password_register");
        String address = request.getParameter("address");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");
        int zipcode = Integer.parseInt(postcode);

        if (customerService.checkEmail(email) != null) {
//                session.setAttribute("invalid_email_error", true);
            return "emailIsExist";
        } else {
            Customer customer = new Customer();
            customer.setFull_name(userName);
            customer.setEmail(email);
            customer.setPassword(pwd);
            customer.setAddress(address);
            customer.setCountry(country);
            customer.setCity(city);
            customer.setZipcode(zipcode);
            customerService.addNewCustomer(customer);
            session.setAttribute("welcome_user", customer);
            return "redirect:/";
        }
    }


    @PostMapping(value = "/checkEmailRegister", produces = "text/html")
    @ResponseBody
    public String checkEmailNo(
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response) {
        String email = request.getParameter("param3");
        try {
            if(customerService.checkEmail(email)!=null) {
//                session.setAttribute("invalid_email_error", true);
                return  "emailIsExist";
            } else {
                return  "";
            }
        } catch (Exception b) {
            b.getMessage();
        }
        return  "emailIsExist";
    }
//=============================================================



// customer info

    @GetMapping("/customer_cms")
    public String viewCustomer_cms(@ModelAttribute(name = "login_register_customer") Customer customer1,
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
        if(customerService.getCustomerById(id)!=null) {

            List<Order> orderList = customerService.getCustomerById(id).getOrderList();
            model.addAttribute("customer_orders", orderList);

            List<CartItems> customerItemsList = customerOrderService.showCustomerItems(id);
            model.addAttribute("customer_items_list",customerItemsList);

        }
        return "contents/customer_order";
    }


}

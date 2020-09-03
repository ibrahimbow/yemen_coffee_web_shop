package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.*;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@ComponentScan

public class MainController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

    private ProductServiceImpl productService;
    private CustomerServiceImpl customerService;
    private AdminServiceImpl adminService;
    private CartItemsServiceImpl cartItemsServiceImpl;
    private CartServiceImpl cartServiceImpl;
    private PaymentServiceImpl paymentServiceImpl;
    private OrderServiceImpl orderServiceImpl;
    private SendEmailServiceImpl sendEmailServiceImpl;



    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setAdminService(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setCartItemsServiceImpl(CartItemsServiceImpl cartItemsServiceImpl) {
        this.cartItemsServiceImpl = cartItemsServiceImpl;
    }

    @Autowired
    public void setCartServiceImpl(CartServiceImpl cartServiceimpl) {
        this.cartServiceImpl = cartServiceimpl;
    }

    @Autowired
    public void setPaymentServiceImpl(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }

    @Autowired
    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @Autowired
    public void setSendEmailServiceImpl(SendEmailServiceImpl sendEmailServiceImpl) {
        this.sendEmailServiceImpl = sendEmailServiceImpl;
    }

    //Display the products
    @GetMapping("/")
    public String viewProducts(Model model, HttpSession session){
        session.setAttribute("productList",productService.getAllProducts());
        // use this to be able to use the quantity of product to be added to the cart item list
        model.addAttribute("quantity_ob",new CartItems());
        // use this to contact us form
        model.addAttribute("user_question", new ReceiveEmailFromUser());

        model.addAttribute("subscriber", new Subscribe());
        return "index";
    }





}

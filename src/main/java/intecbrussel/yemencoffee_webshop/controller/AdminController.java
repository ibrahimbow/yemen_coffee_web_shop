package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@ComponentScan
public class AdminController {

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


    //============================================-Admin section-=================================================
    //--------------------------------------------product-Admin---------------------------------------------------
    @GetMapping("/showAdminProducts")
    public String viewAdminProducts(Model model){
        model.addAttribute("admin_ProductList",productService.getAllProducts());
        return "contents/products_admin";
    }
    @GetMapping("/addNewProductForm")
    public String showNewProductForm(Model model){
        Product product1 = new Product();

        model.addAttribute("add_update_Product",product1);
        return "contents/products_admin_add_new_product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("add_update_Product") Product product1){

        productService.saveProduct(product1);
        return "redirect:/showAdminProducts";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") Long id, Model model) {

        // get Product from the service
        Product productUpdate = productService.getProductById(id);

        // set Product as a model attribute to pre-populate the form
        model.addAttribute("add_update_Product", productUpdate);
        return "contents/products_admin_add_new_product";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable (value = "id") Long id) {

        // call delete product method
        this.productService.deleteProductById(id);
        return "redirect:/showAdminProducts";
    }


    //--------------------------------------------Customer-Admin---------------------------------------------------
    @GetMapping("/show_admin_customers")
    public String viewAdminCustomers(Model model){
        model.addAttribute("admin_customers_list",customerService.getAllCustomers());
        return "contents/customers_admin";
    }

    @GetMapping("/delete_customer/{id}")
    public String deleteCustomer(@PathVariable (value = "id") Long id) {

        // call delete Customer method
        this.customerService.deleteCustomerById(id);
        return "redirect:/show_admin_customers";
    }


    //--------------------------------------------Orders-Admin---------------------------------------------------
    @GetMapping("/show_admin_orders")
    public String viewAdminOrders(Model model){
        model.addAttribute("admin_orders_list",orderServiceImpl.getAllOrders());
        return "contents/orders_admin";
    }

    @GetMapping("/delete_order/{id}")
    public String deleteOrder(@PathVariable (value = "id") Long id) {

        // call delete order method
        this.orderServiceImpl.deleteOrderById(id);
        return "redirect:/show_admin_orders";
    }

    //===========Admin Login =====================

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdminLogin(){
        return "contents/login_admin";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String loginAdmin(HttpSession session,
                             @ModelAttribute(name = "login_admin") Administrator administrator,
                             Model model){

        String adminName = administrator.getAdmin_username();
        String password = administrator.getAdmin_password();

        System.out.println(adminName + "  0 " + password);

        if(adminService.checkingLogin(adminName,password)!=null) {
            // this way to get the name of the user who logged in
            String name = adminService.checkingLogin(adminName,password).getAdmin_fullName();
            System.out.println(adminName + "  0 " + password);
//            model.addAttribute("admin_name", name);
//            session.setAttribute("admin_name", name);
            return "contents/administrator";
        }
        model.addAttribute("invalidError", true);
        return "contents/login_admin";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response ) {
        HttpSession session = request.getSession();

        session.removeAttribute("welcome_user_name");
        session.invalidate();

        return "redirect:/";
    }

}

package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.services.CustomerServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.AdminServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ProductServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.dom4j.rule.Mode;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@ComponentScan
public class MainController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
    private ProductServiceImpl productService;
    private CustomerServiceImpl customerService;
    private AdminServiceImpl adminService;


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



    //Display the products
    @GetMapping("/")
    public String viewProducts(Model model){
        model.addAttribute("productList",productService.getAllProducts());
        return "index";
    }

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
    public String showFormForUpdate(@PathVariable ( value = "id") Long id, Model model) {

        // get Product from the service
        Product productUpdate = productService.getProductById(id);

        // set Product as a model attribute to pre-populate the form
        model.addAttribute("add_update_Product", productUpdate);
        return "contents/products_admin_add_new_product";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") Long id) {

        // call delete product method
        this.productService.deleteProductById(id);
        return "redirect:/showAdminProducts";
    }

//-----------------LOGIN ---------------------
    // to get login form page
    @GetMapping("/login_form")
    public String getLoginForm(){
        return "contents/login_register";
    }

    @RequestMapping(value = "/login_form", method = RequestMethod.POST)
    public String login(HttpSession session,
                        @ModelAttribute(name = "user") Customer customer1, Model model){
    String email = customer1.getEmail();
    String password = customer1.getPassword();

    if(customerService.checkLogin(email,password)!=null) {
        // this way to get the name of the user who logged in
        String name = customerService.checkLogin(email,password).getFull_name();
        model.addAttribute("admin_name", name);
        session.setAttribute("welcome_user_name",name);
        return "redirect:/";
    }
    model.addAttribute("invalidError", true);
    return "contents/login_register";
    }

//-----------------REGISTRATION---------------------

    @GetMapping("/register_form")
    public String getRegisterForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("register_new_customer",customer);
        return "contents/login_register";
    }

    @PostMapping("/register_form")
    public String registerNewCustomer(HttpSession session,
                                      @ModelAttribute("register_new_customer") Customer customer, Model model){
        customerService.addNewCustomer(customer);
        String name = customerService.getCustomerById(customer.getId()).getFull_name();
        session.setAttribute("welcome_user_name",name);
//        model.addAttribute("welcome_customer",name);
        return "redirect:/";
    }



    //===========Admin Login =====================

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String ViewAdminLogin(){
        return "contents/login_admin";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String loginAdmin(HttpSession session,
                             @ModelAttribute(name = "login_admin") Administrator administrator,
                             Model model){

        String adminName = administrator.getAdmin_username();
        String password = administrator.getAdmin_password();

        if(adminService.checkingLogin(adminName,password)!=null) {
            // this way to get the name of the user who logged in
            String name = adminService.checkingLogin(adminName,password).getAdmin_fullName();
            model.addAttribute("admin_name", name);
            session.setAttribute("admin_name", name);
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

    //=========================================

}

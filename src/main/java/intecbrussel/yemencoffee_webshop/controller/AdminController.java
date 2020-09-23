package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.Order;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import intecbrussel.yemencoffee_webshop.services.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;

@Controller
@ComponentScan
public class AdminController {

    private ProductServiceImpl productService;
    private CustomerServiceImpl customerService;
    private AdminServiceImpl adminService;
    private OrderServiceImpl orderServiceImpl;
    private UploadImageService uploadImageImpl;
    private CartItemsServiceImpl cartItemsService;

    @Autowired
    public void setCartItemsService(CartItemsServiceImpl cartItemsService) {
        this.cartItemsService = cartItemsService;
    }

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
    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @Autowired
    public void setUploadImageImpl(UploadImageService uploadImageImpl) {
        this.uploadImageImpl = uploadImageImpl;
    }


    //============================================-Admin section-=================================================
    //============================================== Admin Login =================================================

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdminLogin(){
        return "contents/admin_cms/admin_login";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String loginAdmin(HttpSession session,
                             @ModelAttribute(name = "adminLogin") Administrator administrator,
                             Model model){

        String adminName = administrator.getAdmin_username();
        String password = administrator.getAdmin_password();

        if(adminService.checkingLogin(adminName,password)!=null) {
            // this is way to get the name of the user who logged in
            session.setAttribute("welcome_admin",adminService.checkingLogin(adminName,password));
            return "contents/admin_cms/administrator";
        }
        model.addAttribute("invalidError", true);
        return "contents/admin_cms/admin_login";
    }


    @GetMapping("/setting")
    public String showAdminSettings(){
        return "contents/admin_cms/administrator";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response ) {
        HttpSession session = request.getSession();

        session.removeAttribute("welcome_admin");
        session.invalidate();

        return "redirect:/";
    }

    //--------------------------------------------Admin Products---------------------------------------------------
    @GetMapping("/showAdminProducts")
    public String viewAdminProducts(Model model){
        model.addAttribute("admin_ProductList",productService.getAllProducts());
        return "contents/admin_cms/admin_show_products";
    }
    @GetMapping("/addNewProductForm")
    public String showNewProductForm(Model model){
        Product product1 = new Product();

        model.addAttribute("add_update_Product",product1);
        return "contents/admin_cms/admin_add_new_product";
    }


    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("add_update_Product") Product product1,
                              @RequestParam("imageFile")MultipartFile imageFile,
                              Model model){
            try {
                uploadImageImpl.saveImageService(imageFile);
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", true);
            }

            product1.setImage("/images/" + imageFile.getOriginalFilename());
            productService.saveProduct(product1);
        return "redirect:/showAdminProducts";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") Long id, Model model) {
        // get Product from the service
        Product productUpdate = productService.getProductById(id);

        // set Product as a model attribute to pre-populate the form
        model.addAttribute("add_update_Product", productUpdate);
        return "contents/admin_cms/admin_add_new_product";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable (value = "id") Long id,
                               HttpSession session) {
        // call delete product method
        for(int i = 0; i<cartItemsService.getAllCartItems().size();i++) {
            if (cartItemsService.getAllCartItems().get(i).getProduct().getId() == id) {
                session.setAttribute("wrong",true);
                return "redirect:/showAdminProducts";
            }
        }

            this.productService.deleteProductById(id);

            return "redirect:/showAdminProducts";

    }


    //--------------------------------------------Admin Customers---------------------------------------------------
    @GetMapping("/show_admin_customers")
    public String viewAdminCustomers(Model model){
        model.addAttribute("admin_customers_list",customerService.getAllCustomers());
        return "contents/admin_cms/admin_customers";
    }

    @GetMapping("/delete_customer/{id}")
    public String deleteCustomer(@PathVariable (value = "id") Long id) {
        // call delete Customer method
        this.customerService.deleteCustomerById(id);
        return "redirect:/show_admin_customers";
    }


    //--------------------------------------------Admin Orders---------------------------------------------------
    @GetMapping("/show_admin_orders")
    public String viewAdminOrders(Model model){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        model.addAttribute("admin_orders_list",orderServiceImpl.getAllOrders());
        model.addAttribute("total_price",df.format(orderServiceImpl.getAllOrders().stream().mapToDouble(Order::getTotal_price).sum()));
        return "contents/admin_cms/admin_orders";
    }

    @GetMapping("/delete_order/{id}")
    public String deleteOrder(@PathVariable (value = "id") Long id) {
        // call delete order method
        this.orderServiceImpl.deleteOrderById(id);
        return "redirect:/show_admin_orders";
    }
}

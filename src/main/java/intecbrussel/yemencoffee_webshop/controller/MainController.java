package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.*;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import org.hibernate.annotations.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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




    private final List<CartItems> cartItemsList = new ArrayList<>();


    //Display the products
    @GetMapping("/")
    public String viewProducts(Model model, HttpSession session){
        session.setAttribute("productList",productService.getAllProducts());

        return "index";
    }

//============================================-Cart Items section-=================================================

    @GetMapping("/add_to_cart/{id}")
    public String addToCart(@PathVariable ( value = "id") Long id, HttpSession session){

        if(session.getAttribute("add_to_cart_items")==null){
            this.cartItemsList.add(new CartItems(productService.getProductById(id),1));
            session.setAttribute("add_to_cart_items",this.cartItemsList);
            session.setAttribute("cartItems_quantity",this.cartItemsList.stream().count());
        }else {
            int index =  isExist(id,cartItemsList);
            if(index ==-1) {
                this.cartItemsList.add(new CartItems(productService.getProductById(id),1));
                session.setAttribute("cartItems_quantity",this.cartItemsList.stream().count());
            }else {
                    int quantity = cartItemsList.get(index).getQuantity() +1;
                    this.cartItemsList.get(index).setQuantity(quantity);
                    session.setAttribute("add_to_cart_items",this.cartItemsList);
            }
        }

        return "redirect:/";
    }

    private int isExist(long id, List<CartItems> cartItems){
        for (int i = 0; i <cartItems.size() ; i++) {
            if (cartItems.get(i).getProduct().getId() == id) {
                return i;
            }
        }
        return -1;
    }


    @GetMapping("/cart_items")
    public String viewCartItems(HttpSession session){
        session.setAttribute("add_to_cart_items",this.cartItemsList);
        session.getAttribute("welcome_user");
        return "contents/cart";
    }

    @GetMapping("/deleteCartItems/{id}")
    public String deleteItem(@PathVariable (value = "id") long id) {
        // call delete product method

        for(int i = 0 ; i< cartItemsList.size();i++) {
            if(cartItemsList.get(i).getProduct().getId() == id ) {
                cartItemsList.remove(i);
            }
        }
        return "redirect:/cart_items";
    }



//============================================-Shipping_address section-======================================


    @GetMapping("/check_id")
    public String checkId(RedirectAttributes redirectAttributes, HttpSession session){
        Customer customer = (Customer) session.getAttribute("welcome_user");
        long id = 0;
        if(customer!=null){
            redirectAttributes.addAttribute("id", customer.getId());
        }else{
            redirectAttributes.addAttribute("id", id);
        }
        return "redirect:/shipping_address/{id}";
    }


    @GetMapping("/shipping_address/{id}")
    public String showProcessOfShipping(@PathVariable ( value = "id") Long id,
                                        Model model , HttpSession session){

        session.setAttribute("Total_of_products", cartItemsList.stream().count());
        session.setAttribute("list_cart_items", this.cartItemsList);
        double subtotal = cartItemsList.stream().mapToDouble(s -> s.getProduct().getPrice()).sum();
        double tax = cartItemsList.stream().mapToDouble(s -> s.getProduct().getPrice()).sum() * 0.05;
        double shipping = 15;
        double total = subtotal + tax + shipping;
        session.setAttribute("subtotal_products_price", subtotal);
        session.setAttribute("tax_products_price", ((Math.abs(tax))));
        session.setAttribute("shipping_of_products_price", shipping);
        session.setAttribute("total_of_products_price", total);

        Customer customer;
        if(id!=0){
            customer = customerService.getCustomerById(id);
            // todo try to get the saved payment of the customer
        }else {

            customer = new Customer();


        }
        Payment payment = new Payment();
        model.addAttribute("object_add_shipping_address", customer);
        model.addAttribute("object_add_new_payment",payment);
        return "contents/checkout";
    }


    // this method is to handel the Form on the checkout page
    @PostMapping("/add_shipping_address")
    public String addShippingAddress(@ModelAttribute("object_add_new_payment") Payment payment,
                                     @ModelAttribute("object_add_shipping_address") Customer customer,
                                     HttpSession session){

            session.setAttribute("object_add_shipping_address", customer);
            session.setAttribute("object_add_new_payment", payment);

            //show the info of the cart items
            session.setAttribute("Total_of_products_order", cartItemsList.stream().count());
            session.setAttribute("list_cart_items_order", this.cartItemsList);
            return "contents/confirmation_order";
        }



    @PostMapping("/confirmation")
    public String confirmOrder(@ModelAttribute("object_add_new_payment") Payment payment,
                               @ModelAttribute("object_add_shipping_address") Customer customer,
                               RedirectAttributes redirectAttrs){

        customerService.addNewCustomer(customer);

        double subtotal = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum();
        double tax = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum() * 0.05;
        double shipping = 15;
        double total = subtotal + tax + shipping;
        int quantity=0;




        //1- creat Cart
        Cart cart = new Cart();
        //2- connect the Customer to the cart (add cart to customer)
        cart.setCustomer(customer);
        // 3- Add all the product the have been select by the customer to CartItems
        for (int i = 0; i <cartItemsList.size() ; i++) {
            //4-Creat new CartItems for each time there is a product to add
            CartItems cartItems = new CartItems();
            cartItems.setProduct(cartItemsList.get(i).getProduct());
            cartItems.setQuantity(cartItemsList.get(i).getQuantity());
            cartItems.setCart(cart);
            //5- save the select product to the database inside the table of cart_items
            cartItemsServiceImpl.saveCartItems(cartItems);
            quantity += cartItemsList.get(i).getQuantity();
        }
        // 6- save the rest of all Information to database
        payment.setCustomer(customer);
        cartServiceImpl.saveCart(cart);


        paymentServiceImpl.savePayment(payment);
        // 7- now we can arrange the order
        Order order = new Order(LocalDateTime.now(),customer,cart);
        order.setQuantity(quantity);
        order.setTotal_price(total);
        orderServiceImpl.saveOrder(order);
        //8- get the id for the customer to able to send message to the right person
        redirectAttrs.addAttribute("id", customer.getId());
        return "redirect:/successful/{id}";
    }

    @GetMapping("/successful/{id}")
    public String successful_purchase(@PathVariable ( value = "id") Long id, HttpSession session){
        String message = "Thank you for using our web shop\n You will receive email of confirmation your order";
        session.setAttribute("message",message);

        SendEmailInfo send = new SendEmailInfo();
        send.setToEmail(customerService.getCustomerById(id).getEmail());
        send.setCustomer(customerService.getCustomerById(id));
        send.setProductItemsList(cartItemsList);

        sendEmailServiceImpl.SendEmailSendEmail(send);

        // Remove the list items
        for(int i = 0 ; i< cartItemsList.size();i++) {
            if(cartItemsList.get(i) != null) {
                cartItemsList.remove(i);
            }
        }
        session.removeAttribute("add_to_cart_items");
        session.removeAttribute("list_cart_items_order");
        session.removeAttribute("cartItems_quantity");
        return "contents/successful";
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
    public String showFormForUpdate(@PathVariable ( value = "id") Long id, Model model) {

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
        session.setAttribute("welcome_user_name",name);
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

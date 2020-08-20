package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.*;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private CartServiceImpl cartServiceimpl;
    private PaymentServiceImpl paymentServiceImpl;
    private OrderServiceImpl orderServiceImpl;


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
        this.cartServiceimpl = cartServiceimpl;
    }

    @Autowired
    public void setPaymentServiceImpl(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }

    @Autowired
    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }


    private final List<CartItems> cartItemsList = new ArrayList<>();


    //Display the products
    @GetMapping("/")
    public String viewProducts(Model model, HttpSession session){
        session.setAttribute("productList",productService.getAllProducts());
        return "index";
    }

//============================================-Cart Items section-=================================================

//    @GetMapping("/add_to_cart/{id}")
//    public String addToCart(@PathVariable ( value = "id") Long id,
//                            Model model,
//                            HttpSession session){
//        if(session.getAttribute("add_to_cart_items")==null) {
//
//            CartItems cartItems = new CartItems(productService.getProductById(id));
//            cartItemsServiceImpl.saveCartItems(cartItems);
//            session.setAttribute("add_to_cart_items", cartItemsServiceImpl.getAllCartItems());
//
//        }
//        else if(cartItemsServiceImpl.getCartItemsProductById(id)!=null){
//             int quantity = cartItemsServiceImpl.getCartItemsProductById(id).getQuantity()+1;
//
//
//                cartItemsServiceImpl.saveCartItems(new CartItems(new Cart(),productService.getProductById(id),quantity));
////                model.addAttribute("add_to_cart_items", cartItems);
//                session.setAttribute("cartItems_quantity",cartItemsServiceImpl.getAllCartItems().stream().count());
//                return "redirect:/";
//            }

//        return "redirect:/";
//    }
//

    @GetMapping("/add_to_cart/{id}")
    public String addToCart(@PathVariable ( value = "id") Long id, HttpSession session){

        if(session.getAttribute("add_to_cart_items")==null){
            this.cartItemsList.add(new CartItems(new Cart(),productService.getProductById(id),1));
            session.setAttribute("add_to_cart_items",this.cartItemsList);
            session.setAttribute("cartItems_quantity",this.cartItemsList.stream().count());
        }else {
            int index =  isExist(id,cartItemsList);
            if(index ==-1) {
                this.cartItemsList.add(new CartItems(new Cart(),productService.getProductById(id),1));
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


    @GetMapping("/shipping_address")
    public String showProcessOfShipping(Model model , HttpSession session){
        Customer customer = new Customer();
        Payment payment = new Payment();
        Cart cart = new Cart();

        model.addAttribute("object_add_shipping_address", customer);
        model.addAttribute("object_add_new_payment", payment);
        model.addAttribute("object_add_cart", cart);

        session.setAttribute("Total_of_products",cartItemsList.stream().count());
        session.setAttribute("list_cart_items",this.cartItemsList);
        double subtotal = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum();
        double tax = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum() * 0.05;
        double shipping = 15;
        double total = subtotal + tax + shipping;
        session.setAttribute("subtotal_products_price", subtotal);
        session.setAttribute("tax_products_price", ((Math.abs(tax))));
        session.setAttribute("shipping_of_products_price", shipping);
        session.setAttribute("total_of_products_price", total);

        return "contents/checkout";
    }


//    @PostMapping("/add_shipping_address")
//    public String addShippingAddress(@ModelAttribute("object_add_new_payment") Payment payment,
//                                     @ModelAttribute("object_add_shipping_address") Customer customer,
//                                     Model model){
//
//
//        //======================================
//        model.addAttribute("customer_info_list",customer );
//
//        // show the payment of the customer
////        payment = paymentServiceImpl.getPaymentById(payment.getId());
////        Payment payment = paymentServiceImpl.getAllPayments().stream().filter((customer1) -> customer1.getCustomer().getId().equals(id)).findAny().get();
//
//        model.addAttribute("payment_info_list",payment);
//
//        //show the info of cart items
//        model.addAttribute("Total_of_products_order",cartItemsList.stream().count());
//        model.addAttribute("list_cart_items_order",this.cartItemsList);
//        double subtotal = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum();
//        double tax = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum() * 0.05;
//        double shipping = 15;
//        double total = subtotal + tax + shipping;
//        model.addAttribute("subtotal_products_price", subtotal);
//        model.addAttribute("tax_products_price", ((Math.abs(tax))));
//        model.addAttribute("shipping_of_products_price", shipping);
//        model.addAttribute("total_of_products_price", total);
//
//        //======================================
//        //1- Generate the the number of CART
//        Cart cart = new Cart();
//
//        String uniqueID = UUID.randomUUID().toString();
//        cart.setCartUniNumber(uniqueID);
//        //2- Add the cart items to the database
//        for (int i = 0; i <cartItemsList.size() ; i++) {
//            CartItems cartItems = new CartItems();
//            cartItems.setCart(cart);
//            cartItems.setProduct(cartItemsList.get(i).getProduct());
//            cartItems.setQuantity(cartItemsList.get(i).getQuantity());
//            cart.setCartItems(cartItems);
//            cartItems.setCart(cart);
//            // 3- add the cart items to cart
//            cartServiceimpl.saveCart(cart);
//            cartItemsServiceImpl.saveCartItems(cartItems);
//        }
//        // 4- add the cart to customer
//        customer.setCart(cart);
//        customerService.addNewCustomer(customer);
//        //5-add payment
//        payment.setCustomer(customer);
//        paymentServiceImpl.savePayment(payment);
//
//        return "contents/confirmation_order";
//    }

    // this method is to handel the Form on the checkout page
    @PostMapping("/add_shipping_address")
    public String addShippingAddress(@ModelAttribute("object_add_new_payment") Payment payment,
                                           @ModelAttribute("object_add_shipping_address") Customer customer,
                                           @ModelAttribute Cart cart,
                                     HttpSession session,
                                     Model model){

        session.setAttribute("object_add_shipping_address",customer );

        // show the payment of the customer
//        payment = paymentServiceImpl.getPaymentById(payment.getId());
//        Payment payment = paymentServiceImpl.getAllPayments().stream().filter((customer1) -> customer1.getCustomer().getId().equals(id)).findAny().get();

        session.setAttribute("object_add_new_payment",payment);

        //show the info of cart items
        session.setAttribute("Total_of_products_order",cartItemsList.stream().count());
        session.setAttribute("list_cart_items_order",this.cartItemsList);
        double subtotal = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum();
        double tax = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum() * 0.05;
        double shipping = 15;
        double total = subtotal + tax + shipping;
        session.setAttribute("subtotal_products_price", subtotal);
        session.setAttribute("tax_products_price", ((Math.abs(tax))));
        session.setAttribute("shipping_of_products_price", shipping);
        session.setAttribute("total_of_products_price", total);

        //======================================
        //1- Generate the the number of CART
        String uniqueID = UUID.randomUUID().toString();
        cart.setCartUniNumber(uniqueID);
        //2- Add the cart items to the database
        for (int i = 0; i <cartItemsList.size() ; i++) {
            CartItems cartItems = new CartItems();
            cartItems.setCart(cart);
            cartItems.setProduct(cartItemsList.get(i).getProduct());
            cartItems.setQuantity(cartItemsList.get(i).getQuantity());

            cart.setCartItems(cartItems);
            cartItems.setCart(cart);




            // 3- add the cart items to cart
//            cartServiceimpl.saveCart(cart);
//            cartItemsServiceImpl.saveCartItems(cartItems);
        }
        // 4- add the cart to customer
        customer.setCart(cart);

//        customerService.addNewCustomer(customer);
        //5-add payment
        payment.setCustomer(customer);

//        paymentServiceImpl.savePayment(payment);



        return "contents/confirmation_order";
    }


//
//    @GetMapping("/checkout")
//    public String getShippingInfo(@ModelAttribute("object_add_new_payment") Payment payment,
//                                  @ModelAttribute("object_add_shipping_address") Customer customer,
//                              HttpSession session) {
//
//        // show the information of the customer
//
//
//        session.setAttribute("customer_info_list",customer );
//
//        // show the payment of the customer
////        payment = paymentServiceImpl.getPaymentById(payment.getId());
////        Payment payment = paymentServiceImpl.getAllPayments().stream().filter((customer1) -> customer1.getCustomer().getId().equals(id)).findAny().get();
//
//        session.setAttribute("payment_info_list",payment);
//
//        //show the info of cart items
//        session.setAttribute("Total_of_products_order",cartItemsList.stream().count());
//        session.setAttribute("list_cart_items_order",this.cartItemsList);
//        double subtotal = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum();
//        double tax = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum() * 0.05;
//        double shipping = 15;
//        double total = subtotal + tax + shipping;
//        session.setAttribute("subtotal_products_price", subtotal);
//        session.setAttribute("tax_products_price", ((Math.abs(tax))));
//        session.setAttribute("shipping_of_products_price", shipping);
//        session.setAttribute("total_of_products_price", total);
//
//        return "contents/confirmation_order";
//    }
//


    @PostMapping("/confirmation")
    public String confirmOrder(@ModelAttribute("object_add_new_payment") Payment payment,
                               @ModelAttribute("object_add_shipping_address") Customer customer,
                               @ModelAttribute Cart cart,
                               Model model,
                               HttpSession session){
//
//        model.addAttribute("customer_info_list",customer );
//
//        // show the payment of the customer
////        payment = paymentServiceImpl.getPaymentById(payment.getId());
////        Payment payment = paymentServiceImpl.getAllPayments().stream().filter((customer1) -> customer1.getCustomer().getId().equals(id)).findAny().get();
//
//        model.addAttribute("payment_info_list",payment);

        //show the info of cart items
//        model.addAttribute("Total_of_products_order",cartItemsList.stream().count());
//        model.addAttribute("list_cart_items_order",this.cartItemsList);
//        double subtotal = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum();
//        double tax = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum() * 0.05;
//        double shipping = 15;
//        double total = subtotal + tax + shipping;
//        model.addAttribute("subtotal_products_price", subtotal);
//        model.addAttribute("tax_products_price", ((Math.abs(tax))));
//        model.addAttribute("shipping_of_products_price", shipping);
//        model.addAttribute("total_of_products_price", total);

        //======================================
        //1- Generate the the number of CART


        String uniqueID = UUID.randomUUID().toString();
        cart.setCartUniNumber(uniqueID);
        //2- Add the cart items to the database
        for (int i = 0; i <cartItemsList.size() ; i++) {
            CartItems cartItems = new CartItems();
            cartItems.setCart(cart);
            cartItems.setProduct(cartItemsList.get(i).getProduct());
            cartItems.setQuantity(cartItemsList.get(i).getQuantity());
            cart.setCartItems(cartItems);
            cartItems.setCart(cart);
            // 3- add the cart items to cart
            cartServiceimpl.saveCart(cart);
            cartItemsServiceImpl.saveCartItems(cartItems);
        }
        // 4- add the cart to customer
        customer.setCart(cart);
        customerService.addNewCustomer(customer);
        //5-add payment
        payment.setCustomer(customer);
        paymentServiceImpl.savePayment(payment);

        String message = "Thank you for using our web shop";
        session.setAttribute("message",message);
        return "contents/successful";
    }





    //============================================-Admin section-=================================================
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

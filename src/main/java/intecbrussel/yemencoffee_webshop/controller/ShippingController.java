package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.*;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@ComponentScan
public class ShippingController {


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
    public String showProcessOfShipping(@PathVariable( value = "id") Long id,
                                        Model model , HttpSession session){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        List<CartItems> cartItemsList = (List<CartItems>) session.getAttribute("add_to_cart_items");

        session.setAttribute("Total_of_products", cartItemsList.stream().count());
        session.setAttribute("list_cart_items", cartItemsList);
        double subtotal = cartItemsList.stream().mapToDouble(s -> s.getProduct().getPrice() * s.getQuantity()).sum();
        double tax = cartItemsList.stream().mapToDouble(s -> s.getProduct().getPrice()).sum() * 0.05;
        double shipping = 15;
        double total = subtotal + tax + shipping;
        session.setAttribute("subtotal_products_price", df.format(subtotal));
        session.setAttribute("tax_products_price", df.format(tax));
        session.setAttribute("shipping_of_products_price", shipping);
        session.setAttribute("total_of_products_price", df.format(total));

        Customer customer;
        if(id!=0){
            customer = customerService.getCustomerById(id);
        }else {

            customer = new Customer();


        }
        Payment payment = new Payment();
        model.addAttribute("object_add_shipping_address", customer);
        model.addAttribute("object_add_new_payment",payment);
        return "contents/checkout";
    }


    // this method is to handel the Form on the checkout page
    @GetMapping("/add_shipping_address")
    public String addShippingAddress(@ModelAttribute("object_add_new_payment") Payment payment,
                                     @ModelAttribute("object_add_shipping_address") Customer customer,
                                     HttpSession session){

            List<CartItems> cartItemsList = (List<CartItems>) session.getAttribute("add_to_cart_items");

            session.setAttribute("object_add_shipping_address", customer);
            session.setAttribute("object_add_new_payment", payment);

            //show the info of the cart items
            session.setAttribute("Total_of_products_order", cartItemsList.stream().count());
            session.setAttribute("list_cart_items_order", cartItemsList);
            return "contents/confirmation_order";
        }



    @PostMapping("/confirmation")
    public String confirmOrder(@ModelAttribute("object_add_new_payment") Payment payment,
                               @ModelAttribute("object_add_shipping_address") Customer customer1,
                               RedirectAttributes redirectAttrs,
                               HttpSession session){

        List<CartItems> cartItemsList = (List<CartItems>) session.getAttribute("add_to_cart_items");

        Customer customer = null;

        if(customer1.getId()==null){
            customerService.addNewCustomer(customer1);
            session.setAttribute("object_add_shipping_address", customer1);
        }
        customer = customerService.getCustomerById(customer1.getId());

        double subtotal = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum();
        double tax = cartItemsList.stream().mapToDouble(s->s.getProduct().getPrice()).sum() * 0.05;
        double shipping = 15;
        double total = subtotal + tax + shipping;
        int quantity=0;

        //1- creat Cart
        Cart cart = new Cart();
        //2- connect the Customer to the cart (add cart to customer)
        cart.setCustomer(customer);
        customerService.addNewCustomer(customer);
        cartServiceImpl.saveCart(cart);
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
        paymentServiceImpl.savePayment(payment);
        // 7- now we can arrange the order
        Order order = new Order(LocalDateTime.now(),customer,cart);
        order.setQuantity(quantity);
        order.setTotal_price(total);
        order.setShippingCost(shipping);
        order.setTax(tax);
        orderServiceImpl.saveOrder(order);
        //8- get the id for the customer to able to send message to the right person
        redirectAttrs.addAttribute("id", customer.getId());
        return "redirect:/successful/{id}";
    }




    @GetMapping("/successful/{id}")
    public String successful_purchase(@PathVariable ( value = "id") Long id, HttpSession session){
        List<CartItems> cartItemsList = (List<CartItems>) session.getAttribute("add_to_cart_items");

        String DATE_FORMATTER = "yyyy-MM-dd ' at ' HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        String orderNumber =customerService.getCustomerById(id).getOrderList()
                .stream()
                .mapToInt(Order::getOrder_number)
                .findAny().toString();

        String msg1 = "Thank you for your purchase!";
        String msg2 = "Your order Number is : " + orderNumber;
        String msg3 = "We will email you an order confirmation with details";
        String msg4 = "Order Date: " + LocalDateTime.now().format(formatter);

        session.setAttribute("msg1",msg1);
        session.setAttribute("msg2",msg2);
        session.setAttribute("msg3",msg3);
        session.setAttribute("msg4",msg4);

        SendEmailInfo send = new SendEmailInfo();
        send.setToEmail(customerService.getCustomerById(id).getEmail());
        send.setCustomer(customerService.getCustomerById(id));
        send.setProductItemsList(cartItemsList);

        sendEmailServiceImpl.SendEmailTo(send);

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



}

package intecbrussel.yemencoffee_webshop.controller;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    private ProductServiceImpl productService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }


    //============================================-Cart Items section-=================================================
    @GetMapping("/add_to_cart/{id}")
    public String addToCart(@PathVariable( value = "id") Long id,
                            @ModelAttribute(name = "quantity_ob") CartItems cartItems,
                            HttpSession session){

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        // cartItems.getQuantity() to get the quantity that is selected by the customer
        int quantity = cartItems.getQuantity();
        List<CartItems> cartItemsList = null;
        if(session.getAttribute("add_to_cart_items")==null){
            cartItemsList = new ArrayList<>();
            cartItemsList.add(new CartItems(productService.getProductById(id),quantity));
            session.setAttribute("add_to_cart_items",cartItemsList);
            session.setAttribute("cartItems_quantity",cartItemsList.stream().count());
        }else {
            cartItemsList = (List<CartItems>) session.getAttribute("add_to_cart_items");
            int index =  isExist(id,cartItemsList);
            if(index ==-1) {
                cartItemsList.add(new CartItems(productService.getProductById(id),quantity));
                session.setAttribute("cartItems_quantity",cartItemsList.stream().count());
            }else {
                int plusQuantity = cartItemsList.get(index).getQuantity() +quantity;
                cartItemsList.get(index).setQuantity(plusQuantity);
                session.setAttribute("add_to_cart_items",cartItemsList);
            }
        }
//----------------------------then we produce the list
        cartItemsList = (List<CartItems>) session.getAttribute("add_to_cart_items");
        session.setAttribute("Total_of_products", cartItemsList.stream().count());
        session.setAttribute("list_cart_items", cartItemsList);

        double taxRate = 0.05;
        double subtotal = cartItemsList.stream().mapToDouble(s -> s.getProduct().getPrice() * s.getQuantity()).sum();
        double tax = subtotal * taxRate;
        double shipping = 15;
        double total = subtotal + tax + shipping;

        session.setAttribute("subtotal_products_price", df.format(subtotal));
        session.setAttribute("tax_products_price", df.format(tax));
        session.setAttribute("shipping_of_products_price", shipping);
        session.setAttribute("total_of_products_price", df.format(total));

        return "redirect:/";
    }

    // check if the item is already existed in the list
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

        List<CartItems> cartItems = (List<CartItems>) session.getAttribute("add_to_cart_items");
        session.setAttribute("add_to_cart_items",cartItems);
        session.setAttribute("list_cart_items", cartItems);
        session.getAttribute("welcome_user");

        return "contents/cart";
    }

    @GetMapping("/deleteCartItems/{id}")
    public String deleteItem(@PathVariable (value = "id") long id, HttpSession session) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        // call delete product method
        List<CartItems> cartItemsList = (List<CartItems>) session.getAttribute("add_to_cart_items");
        for(int i = 0 ; i< cartItemsList.size();i++) {
            if(cartItemsList.get(i).getProduct().getId() == id ) {
                cartItemsList.remove(i);
            }
        }
        double subtotal = cartItemsList.stream().mapToDouble(s -> s.getProduct().getPrice() * s.getQuantity()).sum();
        session.setAttribute("cartItems_quantity",cartItemsList.stream().count());
        session.setAttribute("subtotal_products_price", df.format(subtotal));
        return "redirect:/cart_items";
    }


// update the quantity of product
@GetMapping("/updateCartItems/{id}")
    public String updateItems(@PathVariable (value = "id") long id,
                              @RequestParam(name = "productQuantity",value = "productQuantity", required = false, defaultValue = "1") Integer quantity,
                              HttpSession session) {

        List<CartItems> cartItemsList = (List<CartItems>) session.getAttribute("add_to_cart_items");
        for(int i = 0 ; i< cartItemsList.size();i++) {
            if(cartItemsList.get(i).getProduct().getId()==id) {
                cartItemsList.get(i).setQuantity(quantity);
            }
        }
        session.setAttribute("add_to_cart_items",cartItemsList);

    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(2);

    double taxRate = 0.05;
    double subtotal = cartItemsList.stream().mapToDouble(s -> s.getProduct().getPrice() * s.getQuantity()).sum();
    double tax = subtotal * taxRate;
    double shipping = 15;
    double total = subtotal + tax + shipping;

    session.setAttribute("subtotal_products_price", df.format(subtotal));
    session.setAttribute("tax_products_price", df.format(tax));
    session.setAttribute("shipping_of_products_price", shipping);
    session.setAttribute("total_of_products_price", df.format(total));

    return "redirect:/cart_items";
    }

}

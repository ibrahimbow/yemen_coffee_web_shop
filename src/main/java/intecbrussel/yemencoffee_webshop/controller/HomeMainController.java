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


@Controller
@ComponentScan

public class HomeMainController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

    private ProductServiceImpl productService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    //Display home page and show the products
    @GetMapping("/")
    public String viewProducts(Model model, HttpSession session,
                               HttpServletResponse response, HttpServletRequest request){

        session.setAttribute("productList",productService.getAllProducts());
        // use this to be able to use the quantity of product to be added to the cart item list
        model.addAttribute("quantity_ob",new CartItems());
        // use this to contact us form
        model.addAttribute("user_question", new ReceiveEmailFromUser());
        model.addAttribute("subscriber", new Subscribe());
        return "index";
    }
}

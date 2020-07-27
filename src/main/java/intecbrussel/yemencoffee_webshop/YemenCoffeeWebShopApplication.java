package intecbrussel.yemencoffee_webshop;

import intecbrussel.yemencoffee_webshop.model.*;
import intecbrussel.yemencoffee_webshop.repositories.CartItemsRepo;
import intecbrussel.yemencoffee_webshop.repositories.CartRepo;
import intecbrussel.yemencoffee_webshop.repositories.OrderRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class YemenCoffeeWebShopApplication {

    public static void main(String[] args) {
          ConfigurableApplicationContext shoppingCart =
                  SpringApplication.run(YemenCoffeeWebShopApplication.class, args);

        OrderRepo orderRepo = shoppingCart.getBean(OrderRepo.class);

          // product on the shop already exist
            Product product1 =  new Product("mocha","yemen","250gm",19.00,3,"YemenCoffe");
            Product product2 =  new Product("Harazi","yemen","250gm",19.00,3,"YemenCoffe");
            Product product3 =  new Product("Matari","yemen","250gm",19.00,3,"YemenCoffe");
            Product product4 =  new Product("Hofashi","yemen","250gm",19.00,3,"YemenCoffe");

//----------------------------------------
        // second step :
        // The Guest come on the shop and pick up on cart and take around the shop
        // then pick up the things on the cart

        // must be the cartItem list generate with unique number
        CartItems cartItems1 = new CartItems();

        List<Product> cartItemsList = new ArrayList<>();
        cartItemsList.add(product3);
        cartItemsList.add(product4);

        // Add one selected products to the list of cartItems
        cartItems1.setProductList(cartItemsList);


        // add CartItems to the Cart
        Cart cart1 = new Cart();
        cart1.setCartItems(cartItems1);

        // customer info and shipping address
        Customer customer1 =
                new Customer("ibrahim",
                        "lok@olk.com",
                        0121012,
                        "osystraat 5",
                        "belgium",
                        "Antwerpen",
                        2060);

        // we add the payment method but we have only one it just click ok for instance

        // submit the order then we can add the cart to the order
        Order order = new Order(LocalDateTime.now(),customer1,cart1);

        orderRepo.save(order);

        //=========ORDER 2==================
        CartItems cartItems2 = new CartItems();

        List<Product> cartItemsList2 = new ArrayList<>();
        cartItemsList2.add(product1);

        // Add one selected products to the list of cartItems
        cartItems2.setProductList(cartItemsList2);


        // add CartItems to the Cart
        Cart cart2 = new Cart();
        cart2.setCartItems(cartItems2);

        // customer info and shipping address
        Customer customer2 =
                new Customer("Bart",
                        "sa@ko.com",
                        0121012,
                        "SystemStraat 15",
                        "Belgium",
                        "Brussel",
                        1000);

        // we add the payment method but we have only one it just click ok for instance

        // submit the order then we can add the cart to the order
        Order order2 = new Order(LocalDateTime.now(),customer2,cart2);

        orderRepo.save(order2);



        System.out.println(order2);







    }


}

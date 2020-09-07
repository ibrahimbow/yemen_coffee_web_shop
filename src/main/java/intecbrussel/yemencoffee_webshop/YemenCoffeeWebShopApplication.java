package intecbrussel.yemencoffee_webshop;

import intecbrussel.yemencoffee_webshop.controller.HomeMainController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;

@SpringBootApplication
public class YemenCoffeeWebShopApplication {

    public static void main(String[] args){
        // trying to add photo with
        new File(HomeMainController.uploadDirectory).mkdir();
        //-----------------------------
        SpringApplication.run(YemenCoffeeWebShopApplication.class, args);
    }
}

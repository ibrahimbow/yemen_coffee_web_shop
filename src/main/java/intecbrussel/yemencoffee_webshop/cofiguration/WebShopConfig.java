package intecbrussel.yemencoffee_webshop.cofiguration;

import intecbrussel.yemencoffee_webshop.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class WebShopConfig {


    @Bean
    @Scope("prototype")
    public Product product() {
        return new Product();
    }

}

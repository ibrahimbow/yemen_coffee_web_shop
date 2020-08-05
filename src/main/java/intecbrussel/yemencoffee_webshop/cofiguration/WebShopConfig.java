package intecbrussel.yemencoffee_webshop.cofiguration;

import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.services.CustomerService;
import intecbrussel.yemencoffee_webshop.services.CustomerServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ProductService;
import intecbrussel.yemencoffee_webshop.services.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan
public class WebShopConfig {


    @Bean
    @Scope("prototype")
    public Product product() {
        return new Product();
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver()
    {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20848820);
        return multipartResolver;
    }

//
//    @Bean
//    public Customer customer(){
//        return new Customer();
//    }
//    @Bean
//    public CustomerService customerService(){
//        return new CustomerServiceImpl();
//    }


}

package intecbrussel.yemencoffee_webshop.cofiguration;

import intecbrussel.yemencoffee_webshop.email.EmailImpl;
import intecbrussel.yemencoffee_webshop.email.EmailInfo;
import intecbrussel.yemencoffee_webshop.email.SendEmail;
import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.services.*;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.AdminServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;

@Configuration
@ComponentScan
public class WebShopConfig {


    @Bean
    @Scope("prototype")
    public Product product() {
        return new Product();
    }

    @Bean
    @Scope("prototype")
    public Administrator administrator(){
        return new Administrator();
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

    @Bean
    public SendEmail sendEmail(){
        return new EmailImpl();
    }

    @Bean
    public AdminService addAdmin(){
        return new AdminServiceImpl();
    }

//    @Bean
//    public Customer customer(){
//        return new Customer();
//    }
//    @Bean
//    public CustomerService customerService(){
//        return new CustomerServiceImpl();
//    }


}

package intecbrussel.yemencoffee_webshop.cofiguration;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.repositories.implementation.EmailImpl;
import intecbrussel.yemencoffee_webshop.services.*;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.AdminServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.ProductServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.SendEmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan
public class WebShopConfig {

//
//    @Bean
//    @Scope("prototype")
//    public Product product() {
//        return new Product();
//    }

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
    @Scope("prototype")
   public SendEmailInfo sendEmailInfo(){
        return new SendEmailInfo();
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


    @Bean
    @Scope("prototype")
    public SendEmailService sendEmailService(){
        return new SendEmailServiceImpl();
    }
}

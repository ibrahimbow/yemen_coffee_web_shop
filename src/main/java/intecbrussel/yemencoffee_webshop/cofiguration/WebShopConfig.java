package intecbrussel.yemencoffee_webshop.cofiguration;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.ReceiveEmailFromUser;
import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.services.*;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.AdminServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.ProductServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.ReceiveEmailFromUserServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.SendEmailServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan
public class WebShopConfig {

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
    @Primary
    @Scope("prototype")
    public ReceiveEmailFromUser receiveEmailFromUser(){
        return new ReceiveEmailFromUser();
    }

    @Bean
    public AdminService addAdmin(){
        return new AdminServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public SendEmailService sendEmailService(){
        return new SendEmailServiceImpl();
    }

    @Bean
    @Primary
    @Scope("prototype")
    public ReceiveEmailFromUserService receiveEmailFromUserService(){
        return new ReceiveEmailFromUserServiceImpl();
    }
}

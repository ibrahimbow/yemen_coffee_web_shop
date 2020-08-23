package intecbrussel.yemencoffee_webshop;

import intecbrussel.yemencoffee_webshop.controller.MainController;

import intecbrussel.yemencoffee_webshop.repositories.implementation.EmailImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.CustomerServiceImpl;
import intecbrussel.yemencoffee_webshop.services.ImplementationServices.AdminServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ConfigurableApplicationContext;
import java.io.File;

@SpringBootApplication
public class YemenCoffeeWebShopApplication {

    public static void main(String[] args){
//        SpringApplication.run(YemenCoffeeWebShopApplication.class, args);

        new File(MainController.uploadDirectory).mkdir();

          ConfigurableApplicationContext shoppingCart =
                  SpringApplication.run(YemenCoffeeWebShopApplication.class, args);


//        OrderRepo orderRepo = shoppingCart.getBean(OrderRepo.class);
//        ProductRepository productRepository = shoppingCart.getBean(ProductRepository.class);
////
        CustomerServiceImpl customer1 = shoppingCart.getBean(CustomerServiceImpl.class);

        AdminServiceImpl adminService = shoppingCart.getBean("addAdmin",AdminServiceImpl.class);
//        Administrator admin = shoppingCart.getBean("administrator",Administrator.class);
////
//        admin.setAdmin_fullName("ibrahim Alolofi");
//        admin.setAdmin_username("bbg");
//        admin.setAdmin_email("bbg@bbg.com");
//        admin.setAdmin_password("bbg");
//
//        adminService.saveAdmin(admin);


        if(adminService.checkingLogin("bbg", "bbg")!=null){
            System.out.println(" its found");
        }else {
            System.out.println("its not found");
        }

        //========================================================
        //Test of the login (admin or customer)
//        if(customer1.checkLogin("bbg@bbg.com", "bbg402")!=null){
//            System.out.println(" its found");
//        }else {
//            System.out.println("its not found");
//        }
        //========================================================

        //========================================================
        // TEST of sending Email . which I'm gonna use it when the customer confirm the order
        // then will receive an email of payment confirmation and the list of products
//        EmailImpl email = shoppingCart.getBean("sendEmail",EmailImpl.class);
//        email.sendEmail();
        //========================================================

////
//        Customer customer =
//                new Customer();
//        customer.setFull_name("ibra");
//        customer.setAddress("ass");
//        customer.setEmail("bbg@bbg.com");
//        customer.setCity("ccc");
//        customer.setCountry("dsds");
//        customer.setZipcode(222);
//        customer.setPhone(313);
//        customer.setPassword("bbg");
//        customer.setPhone(0465570653);
////
//        customer1.addNewCustomer(customer);
////
//

//        SpringApplication.run(YemenCoffeeWebShopApplication.class, args);
//
//        try {
//
//            BufferedImage bImage = ImageIO.read(new File("http:/localhost:8080/contents/images/YemenCoffee01_c.png"));
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "jpg", bos);
//            byte[] data = bos.toByteArray();
//


//          // product on the shop already exist
//            Product product1 =  new Product("cmocha","contents/images/YemenCoffee01_c.png","250gm",19.00,3,"YemenCoffe");
//            Product product2 =  new Product("Harazi","","250gm",19.00,3,"YemenCoffe");
//            Product product3 =  new Product("Matari","","250gm",19.00,3,"YemenCoffe");
//            Product product4 =  new Product("Hofashi","","250gm",19.00,3,"YemenCoffe");
//
//            productRepository.save(product1);
//            productRepository.save(product2);
//            productRepository.save(product3);
//            productRepository.save(product4);
////
////----------------------------------------

//        // must be the cartItem list generate with unique number
//        CartItems cartItems1 = new CartItems();
//
//        List<Product> cartItemsList = new ArrayList<>();
//        cartItemsList.add(product3);
//        cartItemsList.add(product4);
//
//        // Add one selected products to the list of cartItems
//        cartItems1.setProductList(cartItemsList);
//
//
//        // add CartItems to the Cart
//        Cart cart1 = new Cart();
//        cart1.setCartItems(cartItems1);
//
//        // customer info and shipping address
//        Customer customer1 =
//                new Customer("ibrahim",
//                        "lok@olk.com",
//                        0121012,
//                        "osystraat 5",
//                        "belgium",
//                        "Antwerpen",
//                        2060);
//
//        // we add the payment method but we have only one it just click ok for instance
//
//        // submit the order then we can add the cart to the order
//        Order order = new Order(LocalDateTime.now(),customer1,cart1);
//
//        orderRepo.save(order);

//
//        }catch (Exception e){
//            e.getStackTrace();
//        }
//


    }


}

//package intecbrussel.yemencoffee_webshop.bootstap;
//
//import intecbrussel.yemencoffee_webshop.model.*;
//import intecbrussel.yemencoffee_webshop.repositories.CustomerRepo;
//import intecbrussel.yemencoffee_webshop.repositories.PaymentRepo;
//import intecbrussel.yemencoffee_webshop.repositories.ProductRepository;
//import org.springframework.boot.CommandLineRunner;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BootStrapData implements CommandLineRunner {
//
//    private final ProductRepository productRepository;
//
//    private final CustomerRepo repo;
//
//    private final PaymentRepo paymentRepo;
//
//    private final CartItems cartItems;
//
//    private final Cart cart;
//
//    public BootStrapData(ProductRepository productRepository, CustomerRepo repo, PaymentRepo paymentRepo, CartItems cartItems, Cart cart) {
//        this.productRepository = productRepository;
//        this.repo = repo;
//        this.paymentRepo = paymentRepo;
//        this.cartItems = cartItems;
//        this.cart = cart;
//    }
//
//    public BootStrapData(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    public BootStrapData(CustomerRepo repo) {
//        this.repo = repo;
//    }
//
//    public BootStrapData(PaymentRepo paymentRepo) {
//        this.paymentRepo = paymentRepo;
//    }
//
//
//
//    public BootStrapData(CartItems cartItems) {
//        this.cartItems = cartItems;
//    }
//
//    public BootStrapData(Cart cart) {
//        this.cart = cart;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Product product1 =  new Product("mocha","yemen","250gm",19.00,3,"YemenCoffe");
//        Product product2 =  new Product("Harazi","yemen","250gm",19.00,3,"YemenCoffe");
//        Product product3 =  new Product("Matari","yemen","250gm",19.00,3,"YemenCoffe");
//        Product product4 =  new Product("Hofashi","yemen","250gm",19.00,3,"YemenCoffe");
//
//
//
//        //Choose the list of product that you like
//
//        Product productList = new Product();
//
//        List<Product> cartItemsList = new ArrayList<>();
//        cartItemsList.add(product1);
//        cartItemsList.add(product3);
//        cartItemsList.add(product4);
//
//
//
//        // if you want to buy the select product then you have to fill your info
//        Customer customer1 =
//                new Customer("ibrahim",
//                        "ss@SS.com",
//                        0121012,
//                        "osystraat 5",
//                        "belgium",
//                        "Antwerpen",
//                        2060);
//
//        // if you want to proceed to buy it please insert you payment info
//        Payment payment1 = new Payment();
//        payment1.setName_card("ibrahijm");
//        payment1.setCredit_card_number(43543);
//        payment1.setEx_month(2);
//        payment1.setEx_year(22);
//        payment1.setCvv(111);
//        payment1.setCustomer(customer1);
//
//
//        // please confirm on the summary of you order
//        Order order = new Order();
//        order.setOrder_number(241);
//        order.setOrder_date(LocalDateTime.now());
//        order.setQuantity(cartItemsList.size());
//        order.setDeliver_date(LocalDateTime.now());
//        order.setCustomer(customer1);
//
//
//
//    }
//}

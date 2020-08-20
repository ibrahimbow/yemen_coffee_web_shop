package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    void savePayment(Payment payment);
    Payment getPaymentById(Long id);
    void deletePaymentById(long id);

}

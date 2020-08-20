package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.Payment;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.repositories.PaymentRepo;
import intecbrussel.yemencoffee_webshop.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepo paymentRepo;

    @Autowired
    public void setPaymentRepo(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }


    @Override
    public List<Payment> getAllPayments() {
        return this.paymentRepo.findAll();
    }

    @Override
    public void savePayment(Payment payment) {

        this.paymentRepo.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        Optional<Payment> optional = paymentRepo.findById(id);
        Payment payment = null;
        if (optional.isPresent()) {
            payment = optional.get();
        } else {
            throw new RuntimeException("Payment is not found for id :: " + id);
        }
        return payment;
    }

    @Override
    public void deletePaymentById(long id) {
        this.paymentRepo.deleteById(id);
    }
}

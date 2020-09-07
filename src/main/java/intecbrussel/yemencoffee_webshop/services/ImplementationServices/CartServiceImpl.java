package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.Cart;
import intecbrussel.yemencoffee_webshop.repositories.CartRepo;
import intecbrussel.yemencoffee_webshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartRepo cartRepo;

    @Autowired
    public void setCartRepo(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public List<Cart> getAllCart() {
        return this.cartRepo.findAll();
    }

    @Override
    public void saveCart(Cart cart) {
        this.cartRepo.save(cart);
    }

    @Override
    public Cart getCartById(Long id) {
        Optional<Cart> optional = cartRepo.findById(id);
        Cart cart = null;
        if (optional.isPresent()) {
            cart = optional.get();
        } else {
            throw new RuntimeException("Cart is not found for id :: " + id);
        }
        return cart;
    }

    @Override
    public void deleteCartById(long id) {
        this.cartRepo.deleteById(id);
    }
}

package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.repositories.CartItemsRepo;
import intecbrussel.yemencoffee_webshop.services.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemsServiceImpl implements CartItemsService {

    private CartItemsRepo cartItemsRepo;

    @Autowired
    public void setCartItemsRepo(CartItemsRepo cartItemsRepo) {
        this.cartItemsRepo = cartItemsRepo;
    }

    @Override
    public List<CartItems> getAllCartItems() {
        return this.cartItemsRepo.findAll();
    }

    @Override
    public void saveCartItems(CartItems cartItems) {
        this.cartItemsRepo.save(cartItems);
    }

    @Override
    public CartItems getCartItemsById(Long id) {
        Optional<CartItems> optional = cartItemsRepo.findById(id);
        CartItems cartItems = null;
        if (optional.isPresent()) {
            cartItems = optional.get();
        } else {
            throw new RuntimeException("Cart_Item is not found for id :: " + id);
        }
        return cartItems;
    }

    @Override
    public void deleteCartItemsById(long id) {
        this.cartItemsRepo.deleteById(id);
    }

}

package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.model.Product;
import intecbrussel.yemencoffee_webshop.repositories.CartItemsDao;
import intecbrussel.yemencoffee_webshop.repositories.ProductRepository;
import intecbrussel.yemencoffee_webshop.services.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemsServiceImpl implements CartItemsService {


    private CartItemsDao cartItemsDao;



    @Autowired
    public void setCartItemsDao(CartItemsDao cartItemsDao) {
        this.cartItemsDao = cartItemsDao;
    }



    @Override
    public List<CartItems> getAllCartItems() {
        return cartItemsDao.getAllCartItems();
    }

    @Override
    public void saveCartItems(CartItems cartItems) {
        this.cartItemsDao.createCartItem(cartItems);
    }

    @Override
    public void addCartItems(List<CartItems> cartItems) {
        for (CartItems cartItemList: cartItems) {
            this.cartItemsDao.createCartItem(cartItemList);
        }
    }

    @Override
    public CartItems getCartItemsById(Long id) {
        return this.cartItemsDao.showCartItems(id);
    }

    @Override
    public void deleteCartItemsById(long id) {
        this.cartItemsDao.deleteCartItem(getCartItemsById(id));
    }

    @Override
    public CartItems getCartItemsProductById(Long id) {
        return this.cartItemsDao.checkCartItems(id);
    }

}

package intecbrussel.yemencoffee_webshop.repositories.implementation;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.repositories.CartItemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartItemsDaoImpl implements CartItemsDao {

    private EntityManagerFactory entityManagerFactory;

    private List<CartItems> cartItemsList = new ArrayList<>(); // with this way starts normal the row without null

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean createCartItem(CartItems cartItems) {
        EntityManager entityManagerCartItems = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;
        if(cartItems!=null) {
            try {
                entityTransaction = entityManagerCartItems.getTransaction();
                entityTransaction.begin();
                entityManagerCartItems.persist(cartItems);
                entityManagerCartItems.getTransaction();
                entityTransaction.commit();

            } catch (Exception e) {
                if (entityTransaction != null) {
                    entityTransaction.rollback();
                }
                System.out.println(e.getMessage());
            } finally {
                entityManagerCartItems.close();
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public CartItems showCartItems(Long id) {
        EntityManager ent = entityManagerFactory.createEntityManager();
        String sqlQueryTransfer = "select c from CartItems as c " +
                " where c.id =: id";
        TypedQuery<CartItems> typedQueryTransfer = ent.createQuery(sqlQueryTransfer, CartItems.class);
        typedQueryTransfer.setParameter("id", id);

        return typedQueryTransfer.getSingleResult();
    }

    @Override
    public boolean updateCartItem(CartItems cartItems) {
        EntityManager entityManagerUpdate = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManagerUpdate.getTransaction();
        if(getById(cartItems.getId())!=null) {
            try {
                entityTransaction.begin();
                entityManagerUpdate.merge(cartItems);
                entityTransaction.commit();

            } catch (Exception e) {
                if (entityTransaction != null) {
                    entityTransaction.rollback();
                }
                System.out.println(e.getMessage());
            } finally {
                entityManagerUpdate.close();
            }
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean deleteCartItem(CartItems cartItems) {
        if(getById(cartItems.getId())!=null){
            EntityManager em = entityManagerFactory.createEntityManager();
            try {
                em.getTransaction().begin();
                Administrator adminDelete = em.find(Administrator.class, cartItems.getId());
                if (adminDelete == null) {
                    // to make sure again with throw exception
                    throw new EntityNotFoundException("Can't find CartItems with this ID " + cartItems.getId());
                } else {
                    em.remove(adminDelete);
                    em.getTransaction().commit();
                }
            } catch (Exception e) {
                if (em.getTransaction() != null) {
                    em.getTransaction().rollback();
                }
                System.out.println(e.getMessage());
            } finally {
                em.close();
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public CartItems checkCartItems(long product_id) {
        CartItems cartItems = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sqlQuery = "Select c from CartItems as c " +
                "where c.product.id = :product_id ";
        TypedQuery<CartItems> typedQuery = entityManager.createQuery(sqlQuery,CartItems.class);
        typedQuery.setParameter("product_id", product_id);
        try {
            cartItems = typedQuery.getSingleResult();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            entityManager.close();
        }
        return cartItems;
    }


    //Check if the person is already exists
    public CartItems getById(long cartItem_id) {
        EntityManager ent = entityManagerFactory.createEntityManager();
        String sqlQueryTransfer = "select c from CartItems as c " +
                " where c.id =: cartItem_id";
        TypedQuery<CartItems> typedQueryTransfer = ent.createQuery(sqlQueryTransfer, CartItems.class);
        typedQueryTransfer.setParameter("cartItem_id", cartItem_id);

        return typedQueryTransfer.getSingleResult();
    }




    @Override
    public List<CartItems> getAllCartItems() {
        EntityManager em = entityManagerFactory.createEntityManager();
        String sqlQuery = "SELECT c FROM CartItems as c";
        TypedQuery<CartItems> clientTypedQuery = em.createQuery(sqlQuery, CartItems.class);
        return clientTypedQuery.getResultList();
    }

    @Override
    public void setAllCartItems(List<CartItems> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    public List<CartItems> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(List<CartItems> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }
}

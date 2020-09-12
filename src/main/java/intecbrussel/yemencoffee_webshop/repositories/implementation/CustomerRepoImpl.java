package intecbrussel.yemencoffee_webshop.repositories.implementation;

import intecbrussel.yemencoffee_webshop.model.CartItems;
import intecbrussel.yemencoffee_webshop.repositories.CustomerOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerRepoImpl implements CustomerOrdersRepo {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<CartItems> showCustomerItems(Long id) {
        EntityManager ent = entityManagerFactory.createEntityManager();
        String sqlQueryTransfer = " select c from CartItems as c " +
                "join Cart c2 on c.id = c2.customer.id  " +
                "join CartItems ci on c2.id = ci.cart.id " +
                "where c.id=:id ";
        TypedQuery<CartItems> typedQueryTransfer = ent.createQuery(sqlQueryTransfer, CartItems.class);
        typedQueryTransfer.setParameter("id", id);

        return typedQueryTransfer.getResultList();
    }
}

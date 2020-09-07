package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.Subscribe;
import intecbrussel.yemencoffee_webshop.repositories.SubscribeRepo;
import intecbrussel.yemencoffee_webshop.services.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

@Service
public class SubscribeServiceImpl implements SubscribeService {

    private EntityManagerFactory entityManagerFactory;

    private SubscribeRepo subscribeRepo;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Autowired
    public void setSubscribeRepo(SubscribeRepo subscribeRepo) {
        this.subscribeRepo = subscribeRepo;
    }

    @Override
    public void addNewSubscriber(Subscribe subscribe) {
        subscribeRepo.save(subscribe);
    }

    @Override
    public Subscribe checkEmailSubscribe(String email){
        Subscribe subscribe = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sqlQuery = "Select s from Subscribe as s " +
                "where s.email = :email";
        TypedQuery<Subscribe> typedQuery = entityManager.createQuery(sqlQuery, Subscribe.class);
        typedQuery.setParameter("email", email);
        try {
            subscribe = typedQuery.getSingleResult();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            entityManager.close();
        }
        return subscribe;
    }

}

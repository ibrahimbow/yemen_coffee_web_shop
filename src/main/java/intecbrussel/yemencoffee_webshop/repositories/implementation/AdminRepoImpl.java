package intecbrussel.yemencoffee_webshop.repositories.implementation;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.Customer;
import intecbrussel.yemencoffee_webshop.repositories.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepoImpl implements AdminDao {


    private EntityManagerFactory entityManagerFactory;

    private List<Administrator> admins = new ArrayList<>(); // with this way starts normal the row without null

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public boolean createAdmin(Administrator admin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;
        if(admin!=null) {
            try {
                entityTransaction = entityManager.getTransaction();
                entityTransaction.begin();
                entityManager.persist(admin);
                entityManager.getTransaction();
                entityTransaction.commit();

            } catch (Exception e) {
                if (entityTransaction != null) {
                    entityTransaction.rollback();
                }
                System.out.println(e.getMessage());
            } finally {
                entityManager.close();
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Administrator showAdmin(int id) {
        EntityManager ent = entityManagerFactory.createEntityManager();
        String sqlQueryTransfer = "select p from Administrator as p " +
                " where p.id =: id";
        TypedQuery<Administrator> typedQueryTransfer = ent.createQuery(sqlQueryTransfer, Administrator.class);
        typedQueryTransfer.setParameter("id", id);

        return typedQueryTransfer.getSingleResult();
    }

    @Override
    public boolean updateAdmin(Administrator admin) {
        EntityManager entityManagerUpdate = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManagerUpdate.getTransaction();
        if(getById(admin.getId())!=null) {
            try {
                entityTransaction.begin();
                entityManagerUpdate.merge(admin);
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
    public boolean deleteAdmin(Administrator admin) {
        if(getById(admin.getId())!=null){
            EntityManager em = entityManagerFactory.createEntityManager();
            try {
                em.getTransaction().begin();
                Administrator adminDelete = em.find(Administrator.class, admin.getId());
                if (adminDelete == null) {
                    // to make sure again with throw exception
                    throw new EntityNotFoundException("Can't find person for this ID " + admin.getId());
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
    public List<Administrator> getAllAdmins() {
        EntityManager em = entityManagerFactory.createEntityManager();
        String sqlQuery = "SELECT p FROM Administrator as p";
        TypedQuery<Administrator> clientTypedQuery = em.createQuery(sqlQuery, Administrator.class);
        return clientTypedQuery.getResultList();
    }


    //Check if the person is already exists
    public Administrator getById(int admin_id) {
        EntityManager ent = entityManagerFactory.createEntityManager();
        String sqlQueryTransfer = "select a from Administrator as a " +
                " where a.id =: admin_id";
        TypedQuery<Administrator> typedQueryTransfer = ent.createQuery(sqlQueryTransfer, Administrator.class);
        typedQueryTransfer.setParameter("admin_id", admin_id);

        return typedQueryTransfer.getSingleResult();
    }



    // checking login info
    public Administrator checkLogin(String adminName, String password){
        Administrator administrator = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sqlQuery = "Select a from Administrator as a " +
                "where a.admin_username = :adminName and a.admin_password= :password";
        TypedQuery<Administrator> typedQuery = entityManager.createQuery(sqlQuery, Administrator.class);
        typedQuery.setParameter("adminName", adminName);
        typedQuery.setParameter("password", password);

        try {
            administrator = typedQuery.getSingleResult();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            entityManager.close();
        }
        return administrator;
    }


}

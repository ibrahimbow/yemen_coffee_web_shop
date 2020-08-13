package intecbrussel.yemencoffee_webshop.repositories;

import intecbrussel.yemencoffee_webshop.model.Administrator;

import java.util.List;

public interface AdminDao {

    boolean createAdmin(Administrator admin);
    Administrator  showAdmin(int id);
    boolean updateAdmin(Administrator admin);
    boolean deleteAdmin(Administrator admin);
    List<Administrator> getAllAdmins();
    Administrator checkLogin(String username, String password);

}

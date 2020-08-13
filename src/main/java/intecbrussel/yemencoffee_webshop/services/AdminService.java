package intecbrussel.yemencoffee_webshop.services;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.model.Product;

import java.util.List;

public interface AdminService {
    List<Administrator> getAllAdmins();
    void saveAdmin(Administrator admin);
    Administrator getAdminById(int id);
    void deleteAdminById(int id);
    Administrator checkingLogin(String username, String password);



}

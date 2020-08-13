package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.Administrator;
import intecbrussel.yemencoffee_webshop.repositories.AdminDao;
import intecbrussel.yemencoffee_webshop.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl  implements AdminService {

    private AdminDao adminDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public List<Administrator> getAllAdmins() {
        return this.adminDao.getAllAdmins();
    }

    @Override
    public void saveAdmin(Administrator admin) {
        this.adminDao.createAdmin(admin);
    }

    @Override
    public Administrator getAdminById(int id) {
        return this.adminDao.showAdmin(id);
    }

    @Override
    public void deleteAdminById(int id) {
        this.adminDao.deleteAdmin(getAdminById(id));
    }

    @Override
    public Administrator checkingLogin(String username, String password) {
        return this.adminDao.checkLogin(username,password);
    }


}

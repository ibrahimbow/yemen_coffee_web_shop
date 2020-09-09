package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.repositories.UploadImageDao;
import intecbrussel.yemencoffee_webshop.services.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageServiceImpl implements UploadImageService {

    private UploadImageDao uploadImageDao;

    @Autowired
    public void setUploadImageDao(UploadImageDao uploadImageDao) {
        this.uploadImageDao = uploadImageDao;
    }

    @Override
    public void saveImageService(MultipartFile imageFile) throws Exception {
     this.uploadImageDao.saveImage(imageFile);
    }
}

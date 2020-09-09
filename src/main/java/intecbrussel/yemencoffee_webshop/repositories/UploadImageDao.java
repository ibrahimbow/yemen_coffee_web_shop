package intecbrussel.yemencoffee_webshop.repositories;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageDao {
    void saveImage(MultipartFile imageFile) throws Exception;
}

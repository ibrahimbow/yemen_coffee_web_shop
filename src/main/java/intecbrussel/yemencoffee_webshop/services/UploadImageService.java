package intecbrussel.yemencoffee_webshop.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {

    void saveImageService(MultipartFile imageFile) throws Exception;
}

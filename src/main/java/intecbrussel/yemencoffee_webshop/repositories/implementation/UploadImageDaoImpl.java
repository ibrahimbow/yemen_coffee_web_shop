package intecbrussel.yemencoffee_webshop.repositories.implementation;

import intecbrussel.yemencoffee_webshop.repositories.UploadImageDao;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class UploadImageDaoImpl implements UploadImageDao {

    @Override
    public void saveImage(MultipartFile imageFile) throws Exception {
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        String imagePath = absolutePath + "/src/main/resources/static/images/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(imagePath + imageFile.getOriginalFilename());
        Files.write(path,bytes);
    }
}

package ru.job4j.service;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Image;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ImageService {
    private static final Logger LOG = LogManager.getLogger(ImageService.class);
    private Random random = new Random();

    public List<Image> saveImages(MultipartFile[] files, String fullSavePath, Car car) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile photo : files) {
            if (!photo.isEmpty()) {
                File largeFile;
                String largeName;
                do {
                    largeName = fullSavePath + '/' + "img" + (random.nextInt(Integer.MAX_VALUE) + 1) + ".jpg";
                    largeFile = new File(largeName);
                } while (largeFile.exists());
                String smallName = largeName.replace(".jpg", "-sml.jpg");
                try {
                    largeFile.createNewFile();
                    photo.transferTo(largeFile);
                    Thumbnails.of(largeFile)
                            .size(200, 200)
                            .toFile(Paths.get(largeFile.getAbsolutePath().replace(".jpg", "-sml.jpg")).toFile());
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
                Image image = new Image(photo.getName(), largeFile.getName(), new File(smallName).getName());
                image.setCar(car);
                images.add(image);
            }
        }
        return images;
    }

    public File foundImage(String requestedFile, String fullSavePath) {
        return new File(fullSavePath + '/' + requestedFile);
    }
}

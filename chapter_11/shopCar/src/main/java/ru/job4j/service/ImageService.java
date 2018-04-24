package ru.job4j.service;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.model.car.Image;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ImageService {
    private static final Logger LOG = LogManager.getLogger(ImageService.class);
    private Random random = new Random();

    public Image prepareImage(FileItem fileItem, String fullSavePath) {
        File largeFile;
        String largeName;
        do {
            largeName = fullSavePath + '/' + "img" + (random.nextInt(Integer.MAX_VALUE) + 1) + ".jpg";
            largeFile = new File(largeName);
        } while (largeFile.exists());
        String smallName = largeName.replace(".jpg", "-sml.jpg");
        try {
            largeFile.createNewFile();
            fileItem.write(largeFile);
            Thumbnails.of(largeFile)
                    .size(200, 200)
                    .toFile(Paths.get(largeFile.getAbsolutePath().replace(".jpg", "-sml.jpg")).toFile());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new Image(fileItem.getName(), largeFile.getName(), new File(smallName).getName());
    }

    public File foundImage(String requestedFile, String fullSavePath) {
        return new File(fullSavePath + '/' + requestedFile);
    }
}

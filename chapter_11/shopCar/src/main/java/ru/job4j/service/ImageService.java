package ru.job4j.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Random;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ImageService {
    private static final Logger LOG = LogManager.getLogger(ImageService.class);
    private Random random = new Random();

    public String prepareImage(FileItem fileItem, String fullSavePath) {
        File file;
        do {
            StringBuilder sb = new StringBuilder(fullSavePath);
            sb.append('/').append(random.nextInt(Integer.MAX_VALUE)  + 1).append(fileItem.getName());
            file = new File(sb.toString());
        } while (file.exists());
        try {
            file.createNewFile();
            fileItem.write(file);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return file.getName();
    }

    public File foundImage(String requestedFile, String fullSavePath) {
        return new File(fullSavePath + '/' + requestedFile);
    }
}

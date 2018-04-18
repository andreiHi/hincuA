package ru.job4j.actions;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.model.Advert;
import ru.job4j.model.car.Car;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class CreateAdvert implements Action {
    private static final Logger LOG = LogManager.getLogger(CreateAdvert.class);
    private DiskFileItemFactory factory;

    public CreateAdvert() {
        this.factory = new DiskFileItemFactory();
    }

    @Override
    public String action(HttpServletRequest req, JSONObject json) {
        Advert advert = new Advert();
        Car car = new Car();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<byte[]> images = new ArrayList<>();
        try {
            List fileItems = upload.parseRequest(req);
            Iterator iterator = fileItems.iterator();
            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    byte[] image = fileItem.get();
                    images.add(image);
                    iterator.remove();
                    System.out.println(fileName + " " + image.length);
                } else {
                    String paramName = fileItem.getFieldName();
                    String paramValue = fileItem.getString();
                    System.out.println(paramName + " " + paramValue);

                }
            }
            System.out.println(images.size() + " count of images");
            System.out.println(fileItems.size() + " size of file items");
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void createModels(String paramName, String paramValue) {

    }
}

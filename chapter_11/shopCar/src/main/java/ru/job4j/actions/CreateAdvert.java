package ru.job4j.actions;


import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.model.Advert;
import ru.job4j.model.User;
import ru.job4j.model.car.Brand;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Image;
import ru.job4j.model.car.Model;
import ru.job4j.model.car.parts.*;
import ru.job4j.service.AdvertService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class CreateAdvert implements Action {
    private static final Logger LOG = LogManager.getLogger(CreateAdvert.class);
    private DiskFileItemFactory factory;
    private Random random = new Random();
    public CreateAdvert() {
        this.factory = new DiskFileItemFactory();
    }

    @Override
    public String action(HttpServletRequest req, JSONObject json) {
        String fullSavePath = (String) req.getServletContext().getAttribute("fullSavePath");
        long id = 0;
        Advert advert = new Advert((User) req.getSession().getAttribute("user"));
        Brand brand = new Brand();
        Model model = new Model();
        Engine engine = new Engine();
        Car car = new Car(engine, brand, model, advert);
        advert.setCar(car);
        List<Image> images = new ArrayList<>();
        car.setImages(images);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List fileItems = upload.parseRequest(req);
            for (Object fileIt : fileItems) {
                FileItem fileItem = (FileItem) fileIt;
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    String path = prepareImage(fileItem, fullSavePath);
                    images.add(new Image(fileName, path, car));
                } else {
                    switch (fileItem.getFieldName()) {
                        case "brand" : brand.setId(Long.valueOf(fileItem.getString()));
                            break;
                        case "model": model.setId(Long.valueOf(fileItem.getString()));
                            break;
                        case "carcass" : car.setCarcass(Carcass.valueOf(fileItem.getString()));
                            break;
                        case "transmission" : car.setTransmission(Transmission.valueOf(fileItem.getString()));
                            break;
                        case "gearbox" : car.setGearBox(Gearbox.valueOf(fileItem.getString()));
                            break;
                        case "engineType" : engine.setFuelType(EngineType.valueOf(fileItem.getString()));
                            break;
                        case "description" : advert.setDescription(fileItem.getString());
                            break;
                        case "volume" : engine.setVolume(Integer.valueOf(fileItem.getString()));
                            break;
                        case "power" : engine.setPower(Integer.parseInt(fileItem.getString()));
                            break;
                        case "mileage" : car.setMileage(Integer.parseInt(fileItem.getString()));
                            break;
                        case "price" : advert.setPrice(Integer.parseInt(fileItem.getString()));
                            break;
                        case "year" : car.setYear(Integer.parseInt(fileItem.getString()));
                            break;
                        default: break;
                    }
                }
            }
            AdvertService service = new AdvertService();
            id = service.save(advert);
        } catch (FileUploadException e) {
            LOG.error(e.getMessage(), e);
        }
        return new Gson().toJson(id);
    }

    private String prepareImage(FileItem fileItem, String fullSavePath) {
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

}

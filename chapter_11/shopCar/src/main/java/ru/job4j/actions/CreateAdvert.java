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
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;


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
        CreateAd createAd = new CreateAd();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            createAd.prepareFileItems(fileItems, fullSavePath);
            id = createAd.saveAdvert((User) req.getSession().getAttribute("user"));
        } catch (FileUploadException e) {
            LOG.error(e.getMessage(), e);
        }
        return new Gson().toJson(id);
    }

    class CreateAd {
        private Brand brand = new Brand();
        private Model model = new Model();
        private Engine engine = new Engine();
        private List<Image> images = new ArrayList<>();
        private Advert advert = new Advert();
        private Car car = new Car(engine, brand, model, advert, images);
        private final HashMap<String, Consumer<FileItem>> map = new HashMap<String, Consumer<FileItem>>();
        {
            map.put("brand",        fileItem -> brand.setId(Long.valueOf(fileItem.getString())));
            map.put("model",        fileItem -> model.setId(Long.valueOf(fileItem.getString())));
            map.put("carcass",      fileItem -> car.setCarcass(Carcass.valueOf(fileItem.getString())));
            map.put("transmission", fileItem -> car.setTransmission(Transmission.valueOf(fileItem.getString())));
            map.put("gearbox",      fileItem -> car.setGearBox(Gearbox.valueOf(fileItem.getString())));
            map.put("engineType",   fileItem -> engine.setFuelType(EngineType.valueOf(fileItem.getString())));
            map.put("description",  fileItem -> advert.setDescription(fileItem.getString()));
            map.put("volume",       fileItem -> engine.setVolume(Integer.valueOf(fileItem.getString())));
            map.put("power",        fileItem -> engine.setPower(Integer.valueOf(fileItem.getString())));
            map.put("mileage",      fileItem -> car.setMileage(Integer.valueOf(fileItem.getString())));
            map.put("price",        fileItem -> advert.setPrice(Integer.valueOf(fileItem.getString())));
            map.put("year",         fileItem -> car.setYear(Integer.valueOf(fileItem.getString())));
            advert.setCar(car);
        }

        private void prepareFileItems(List<FileItem> fileItems, String savePath) {
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    String path = prepareImage(fileItem, savePath);
                    images.add(new Image(fileName, path, car));
                } else {
                    map.get(fileItem.getFieldName()).accept(fileItem);
                }
            }
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

        long saveAdvert(User user) {
            this.advert.setUser(user);
            return new AdvertService().save(this.advert);
        }
    }
}

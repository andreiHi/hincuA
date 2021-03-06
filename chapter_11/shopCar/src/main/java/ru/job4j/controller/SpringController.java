package ru.job4j.controller;

import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.ItemsIndexForm;
import ru.job4j.model.Advert;
import ru.job4j.model.AdvertForm;
import ru.job4j.model.car.Brand;
import ru.job4j.model.car.Model;
import ru.job4j.model.usersmodels.User;
import ru.job4j.service.AdvertService;
import ru.job4j.service.BrandService;
import ru.job4j.service.ImageService;
import ru.job4j.service.ModelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Controller("springController")
public class SpringController {
    private static final Logger LOG = LogManager.getLogger(SpringController.class);
    private BrandService brandService;
    private ModelService modelService;
    private AdvertService advertService;

    @Autowired
    public SpringController(BrandService brandService, ModelService modelService, AdvertService advertService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.advertService = advertService;
    }

    @PostMapping(value = "/getItems", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getItems(HttpSession session) {
        User user = (User) session.getAttribute("user");
        JSONObject jsonObject = new ItemsIndexForm().setupItems();
        user = user == null ? User.UNKNOWN_USER : user;
        jsonObject.put("user", user.toJson());
        jsonObject.put("brands", brandService.getAllBrandsToJson());
        return ResponseEntity.ok(jsonObject.toJSONString());
    }
    //@RequestParam(value = "id") Long id
    @GetMapping(value = "/getModels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getModels1(@ModelAttribute Brand brand) {
        JSONObject json = new JSONObject();
        List<Model> models = modelService.findByBrand(brand);
        json.put("models", new Gson().toJson(models));
        return ResponseEntity.ok(json.toJSONString());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createAdvert(@ModelAttribute AdvertForm advertForm, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        String answer;
        if (user != null) {
            answer = "ok";
            String savePath = (String) req.getServletContext().getAttribute("fullSavePath");
            Advert advert = advertForm.createNewAdvert(savePath);
            advert.setUser(user);
            advertService.save(advert);
        } else {
            answer = "login";
        }
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/img", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> deliveryImage(@RequestParam(value = "name") String name, HttpServletRequest req) throws FileNotFoundException {
        String savePath = (String) req.getServletContext().getAttribute("fullSavePath");
        File file = new ImageService().foundImage(name, savePath);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        String contentType = req.getServletContext().getMimeType(file.getName());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(new InputStreamResource(new FileInputStream(file)));
    }
    //todo
    @PostMapping(value = "/setSold", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> soldCar(@RequestBody Map map) {
        String state = (String) map.get("state");
        String id = (String) map.get("id");
        advertService.changeState(Long.valueOf(id), state);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/adverts", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> data(@RequestBody Map request, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        List<Advert> adverts = advertService.getAdverts(user, request);
        JSONObject jsonObject = new JSONObject();
        adverts.forEach(advert ->  jsonObject.put(advert.getId(), advert.toJson()));
        return ResponseEntity.ok(jsonObject.toJSONString());
    }

}

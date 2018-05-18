package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.actions.AdvertSelector;
import ru.job4j.actions.CarSold;
import ru.job4j.actions.GetModels;
import ru.job4j.actions.ItemsIndexForm;
import ru.job4j.model.Advert;
import ru.job4j.model.AdvertForm;
import ru.job4j.model.usersmodels.User;
import ru.job4j.service.ImageService;

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
@Controller
public class SpringController {
    private static final Logger LOG = LogManager.getLogger(SpringController.class);


    @PostMapping(value = "/getItems", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getItems(HttpSession session) {
        User user = (User) session.getAttribute("user");
        JSONObject jsonObject = new ItemsIndexForm().setupItems();
        user = user == null ? User.UNKNOWN_USER : user;
        jsonObject.put("user", user.toJson());
        return ResponseEntity.ok(jsonObject.toJSONString());
    }

    @PostMapping(value = "/getModels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getModels(@RequestBody GetModels models) {
        return ResponseEntity.ok(models.modelsById());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createAdvert(@ModelAttribute AdvertForm advertForm, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        String savePath = (String) req.getServletContext().getAttribute("fullSavePath");
        String create = advertForm.createNewAdvert(user, savePath);
        return ResponseEntity.ok(create);
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

    @PostMapping(value = "/setSold", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> soldCar(@RequestBody CarSold carSold) {
        boolean result = carSold.changeState();
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/adverts", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> data(@RequestBody Map request, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        List<Advert> adverts = new AdvertSelector().getAdverts(user, request);
        JSONObject jsonObject = new JSONObject();
        adverts.forEach(advert ->  jsonObject.put(advert.getId(), advert.toJson()));
        return ResponseEntity.ok(jsonObject.toJSONString());
    }

}
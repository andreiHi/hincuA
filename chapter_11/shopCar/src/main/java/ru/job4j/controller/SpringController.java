package ru.job4j.controller;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.actions.GetModels;
import ru.job4j.actions.ItemsIndexForm;
import ru.job4j.model.AdvertForm;
import ru.job4j.model.usersmodels.LoginForm;
import ru.job4j.model.usersmodels.RegistrationForm;
import ru.job4j.model.usersmodels.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Controller
public class SpringController {
    private static final Logger LOG = LogManager.getLogger(SpringController.class);
    private static final Gson GSON = new Gson();
    @PostMapping(value = "/data", produces = "application/json")
    public ResponseEntity<String> data(@RequestBody Map request, HttpSession session) throws IOException {
        String action = (String) request.get("action");
//    String data = (String) request.get("data");
//      System.out.println(action + "  "  );
//        System.out.println(request);
        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> login(@RequestBody LoginForm loginForm, HttpSession session) {
        boolean check = loginForm.checkLoginForm();
        if (check) {
            session.setAttribute("user", loginForm.getUser());
        }
        return ResponseEntity.ok(check);
    }

    @PostMapping(value = "/logOut", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> logOut(HttpSession session) {
        session.removeAttribute("user");
        return  ResponseEntity.ok(true);
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public@ResponseBody ResponseEntity<String> registration(@RequestBody RegistrationForm form) {
        return ResponseEntity.ok(GSON.toJson(form.createIfValid()));
    }

    @PostMapping(value = "/getItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getItems(HttpSession session) {
        User user = (User) session.getAttribute("user");
        JSONObject jsonObject = new ItemsIndexForm().setupItems();
        user = user == null ? User.UNKNOWN_USER : user;
        jsonObject.put("user", user.toJson());
        return ResponseEntity.ok(jsonObject.toJSONString());
    }

    @PostMapping(value = "/getModels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getModels(@RequestBody GetModels models) {
        return ResponseEntity.ok(models.modelsById());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createAdvert(@ModelAttribute AdvertForm advertForm, HttpSession session, HttpServletRequest req) {
        User user = (User) session.getAttribute("user");
        String savePath = (String) req.getServletContext().getAttribute("fullSavePath");
        String create = advertForm.createNewAdvert(user, savePath);
        return ResponseEntity.ok(create);
    }
}

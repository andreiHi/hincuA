package ru.job4j.controller;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.model.usersmodels.LoginForm;
import ru.job4j.model.usersmodels.RegistrationForm;
import ru.job4j.model.usersmodels.User;

import javax.servlet.http.HttpSession;

/**
 * Класс обслуживает запросы связанные с пользователем.
 * @author Hincu Andrei (andreih1981@gmail.com)on 18.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    private static final Gson GSON = new Gson();

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> login(@RequestBody LoginForm loginForm, HttpSession session) {
        boolean check = loginForm.checkLoginForm();
        if (check) {
            session.setAttribute("user", loginForm.getUser());
        }
        return ResponseEntity.ok(check);
    }

    @PostMapping(value = "/logOut", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> logOut(HttpSession session) {
        session.removeAttribute("user");
        return  ResponseEntity.ok(true);
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public@ResponseBody
    ResponseEntity<String> registration(@RequestBody RegistrationForm form) {
        return ResponseEntity.ok(GSON.toJson(form.createIfValid()));
    }

    @PostMapping(value = "/checkLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        user = user == null ? User.UNKNOWN_USER : user;
        return ResponseEntity.ok().body(user.toJson().toJSONString());
    }
}

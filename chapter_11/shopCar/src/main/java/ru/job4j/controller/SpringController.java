package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.job4j.model.usersmodels.LoginForm;
import ru.job4j.model.usersmodels.RegistrationForm;

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

    @PostMapping(value = "/data", produces = "application/json")
    public ResponseEntity<String> data(@RequestBody Map request, HttpSession session) throws IOException {
       String action = (String) request.get("action");
//    String data = (String) request.get("data");
//      System.out.println(action + "  "  );
//        System.out.println(request);
        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<Boolean> login(@RequestBody LoginForm loginForm, HttpSession session) {
        boolean check = loginForm.checkLoginForm();
        if (check) {
            session.setAttribute("user", loginForm.getUser());
        }
        return ResponseEntity.ok(check);
    }
    @PostMapping(value = "/registration", produces = "application/json")
    public ResponseEntity<String> registration(@RequestBody RegistrationForm form) {
        System.out.println(form.toString());
        String s = form.createIfValid();
        System.out.println(s);
        return ResponseEntity.ok(s);

    }
}

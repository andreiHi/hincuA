package ru.job4j.actions;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.model.User;
import ru.job4j.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class CreateUser implements Action {
    private static final Logger LOG = LogManager.getLogger(CreateUser.class);

    @Override
    public String action(HttpServletRequest req, JSONObject json) {
        String login = (String) json.get("login");
        String password = (String) json.get("password");
        String email = (String) json.get("email");
        String phone = (String) json.get("phone");
        UserService service = new UserService();
        User user = new User(login, email, password, phone);
        String result = service.saveIfValid(user);
        return new Gson().toJson(result);
    }
}

package ru.job4j.actions;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.model.User;
import ru.job4j.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserInOut implements Action {
    private static final Logger LOG = LogManager.getLogger(UserInOut.class);

    @Override
    public String action(HttpServletRequest req, JSONObject json) {
        HttpSession session = req.getSession();
        boolean exist = false;
        User userLogin = (User) session.getAttribute("user");
        if (userLogin == null) {
            System.out.println(json.toJSONString());
            String login = (String) json.get("login");
            String password = (String) json.get("password");
            UserService service = new UserService();
            User user = service.getUserByLogin(login);
            if (user.checkPassword(password)) {
                session.setAttribute("user", user);
                exist = true;
            }
        } else {
            session.removeAttribute("user");
        }
        System.out.println(exist);
        return new Gson().toJson(exist);
    }
}

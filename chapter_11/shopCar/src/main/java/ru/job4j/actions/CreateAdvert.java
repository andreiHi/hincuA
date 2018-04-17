package ru.job4j.actions;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class CreateAdvert implements Action {
    private static final Logger LOG = LogManager.getLogger(CreateAdvert.class);

    @Override
    public String action(HttpSession session, JSONObject json) {
//        System.out.println("dfdfdfdfdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
//        System.out.println(json.toJSONString());
//        String l = (String) json.get("form");
//        String s="";
//todo
//        System.out.println(s);
        return null;
    }
}

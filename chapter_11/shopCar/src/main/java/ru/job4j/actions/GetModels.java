package ru.job4j.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import ru.job4j.service.ModelService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class GetModels implements Action {
    private static final Logger LOG = LogManager.getLogger(GetModels.class);

    @Override
    public String action(HttpServletRequest req, JSONObject json) {
        long id = Long.valueOf((String) json.get("id"));
        ModelService service = new ModelService();
        JSONObject jsonObject = new JSONObject();
        String models = service.getModelsToJson(id);
        jsonObject.put("models", models);
        //System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }
}

package ru.job4j.actions;

import org.json.simple.JSONObject;
import ru.job4j.service.oldservicehibernate.ModelService;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class GetModels {

    private int id;

    public String modelsById() {
        ModelService service = new ModelService();
        JSONObject jsonObject = new JSONObject();
        String models = service.getModelsToJson(id);
        jsonObject.put("models", models);
        return jsonObject.toJSONString();
    }

    public GetModels() {
    }

    @Override
    public String toString() {
        return String.format("GetModels{id = '%s'}", id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

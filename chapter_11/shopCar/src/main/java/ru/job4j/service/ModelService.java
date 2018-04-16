package ru.job4j.service;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.dao.modeldao.ModelImpl;
import ru.job4j.model.car.Model;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ModelService {
    private static final Logger LOG = LogManager.getLogger(ModelService.class);
    private ModelImpl service;

    public ModelService() {
        this.service = new ModelImpl();
    }
    public List<Model> getModelsByBrandId(long id) {
        service.openCurrentSession();
        List<Model> models = service.getModelsByBrandId(id);
        service.closeCurrentSession();
        return models;
    }

    public String getModelsToJson(long id) {
        return new Gson().toJson(getModelsByBrandId(id));
    }
}

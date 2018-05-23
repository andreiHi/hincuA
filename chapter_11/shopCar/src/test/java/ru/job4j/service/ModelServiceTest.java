package ru.job4j.service;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.car.Brand;
import ru.job4j.model.car.Model;
import ru.job4j.service.oldservicehibernate.BrandService;
import ru.job4j.service.oldservicehibernate.ModelService;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 06.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ModelServiceTest {
    private ModelService service;
    private BrandService brandService;
    @Before
    public void start() {
        service = new ModelService();
        brandService = new BrandService();
    }

    @Test
    public void name() {
        Brand brand = new Brand("BMW");
        brandService.save(brand);
        Model model = new Model("1 ser", brand);
        service.save(model);
        List<Model> m = Collections.singletonList(model);
        List<Model> models = service.getModelsByBrandId(brand.getId());
        assertTrue(m.containsAll(models));
        assertTrue(models.containsAll(m));
    }

    @Test
    public void whenWasCalledModelToJson() {
        Brand brand = new Brand("BMW");
        long id =  brandService.save(brand);
        Model model = new Model("1 ser", brand);
        service.save(model);
        String ex = new Gson().toJson(Collections.singletonList(model));
        System.out.println(ex);
        System.out.println(service.getModelsToJson(id));
        assertTrue(ex.equalsIgnoreCase(service.getModelsToJson(id)));
    }
}
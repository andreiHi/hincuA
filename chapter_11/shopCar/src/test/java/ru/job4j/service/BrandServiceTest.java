package ru.job4j.service;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.car.Brand;
import ru.job4j.dao.oldservicehibernate.BrandService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 06.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class BrandServiceTest {
//    private BrandService service;
//
//    @Before
//    public void start() {
//        service = new BrandService();
//    }
//
//    @Test
//    public void whenWasSavedSomeBrands() {
//        Brand brand = new Brand("BMW");
//        Brand brand2 = new Brand("AUDI");
//        service.save(brand);
//        service.save(brand2);
//        List<Brand> b = Arrays.asList(brand, brand2);
//        List<Brand> brands = service.getAllBrands();
//        assertTrue(b.containsAll(brands));
//        assertTrue(brands.containsAll(b));
//    }
//
//    @Test
//    public void whenWasCalledGetAllToJson() {
//        Brand brand = new Brand("BMW");
//        service.save(brand);
//        String result = service.getAllBrandsToJson();
//        String ex = new Gson().toJson(service.getAllBrands());
//        assertTrue(ex.equalsIgnoreCase(result));
//    }
}
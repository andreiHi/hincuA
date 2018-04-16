package ru.job4j.service;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.dao.modeldao.BrandImpl;
import ru.job4j.model.car.Brand;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class BrandService {
    private static final Logger LOG = LogManager.getLogger(BrandService.class);
    private BrandImpl brandDao;

    public BrandService() {
        this.brandDao = new BrandImpl();
    }

    public List<Brand> getAllBrands() {
        brandDao.openCurrentSession();
        List<Brand> brands = brandDao.getAll();
        brandDao.closeCurrentSession();
        return brands;
    }
    public String getAllBrandsToJson() {
       return new Gson().toJson(getAllBrands());
    }
}

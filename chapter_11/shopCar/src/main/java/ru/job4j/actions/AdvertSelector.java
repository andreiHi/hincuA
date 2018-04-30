package ru.job4j.actions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.model.Advert;
import ru.job4j.model.User;
import ru.job4j.service.AdvertService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AdvertSelector {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/YYYY");
    private static final Logger LOG = LogManager.getLogger(AdvertSelector.class);
    private boolean today;
    private boolean photo;
    private String select;
    private String brand;
    private String carcass;
    private String engineType;
    private String gearbox;
    private String mileage;
    private String model;
    private String transmission;
    private String volume;
    private String year;
    private String fromPrice;
    private String toPrice;

    public AdvertSelector() {

    }

    public List<Advert> getAdverts(HttpServletRequest req) {
        List<Advert> adverts;
        if("byUser".equals(this.select)) {
            User user = (User) req.getSession().getAttribute("user");
            adverts = new AdvertService().getAdvertsByUser(user);
        }
        else {
            StringBuilder builder = new StringBuilder("from Advert as a");
            if (this.photo) {
                builder.append(" join fetch Image as i on i.car = a.car");
            }
            if (this.today) {
                Date today = new Date();
                builder.append(" where a.data  >= '").append(FORMAT.format(today)).append("'");
            }
            if ("searchForm".equals(this.select)) {
                builder = addFilters(builder);
            }
            builder.append(" order by a.data DESC");
            String query = builder.toString();
            if (query.contains("join")) {
                adverts = new AdvertService().getByQueryWithJoin(query);
            } else {
                adverts = new AdvertService().getByQuery(query);
            }
        }
        return adverts;
    }

    private StringBuilder addFilters(StringBuilder builder) {
        if (this.today) {
            builder.append(" and ");
        } else {
            builder.append(" where ");
        }
        // brand='9039, carcass='Hatchback,
//        if (!brand.equals("0")) {
//AdvertSelector{today= false, photo='false', select='searchForm', brand='9039,
// carcass='Sedan', engineType='Diesel', gearbox='Avtomat', mileage='1', model='9041', transmission='Front_Wheel_Drive', volume='3', year='3', fromPrice='250000', toPrice='}
//        }
        System.out.println(this);
        // engineType='Benzine, gearbox='Avtomat, mileage='2, model='9043, transmission='Rear_Drive, volume='1, year='2}
        return builder;
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarcass() {
        return carcass;
    }

    public void setCarcass(String carcass) {
        this.carcass = carcass;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(String fromPrice) {
        this.fromPrice = fromPrice;
    }

    public String getToPrice() {
        return toPrice;
    }

    public void setToPrice(String toPrice) {
        this.toPrice = toPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("AdvertSelector{today= ").append(today).append(", photo='" ).append(photo)
                .append("', select='").append(select).append("', brand='").append(brand).append(", carcass='").append(carcass)
                .append("', engineType='").append(engineType).append("', gearbox='").append(gearbox).append("', mileage='").append(mileage)
                .append("', model='").append(model).append("', transmission='").append(transmission).append("', volume='").append(volume)
                .append("', year='").append(year).append("', fromPrice='").append(fromPrice).append("', toPrice='").append(toPrice).append('}');
        return builder.toString();
    }
}

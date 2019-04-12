package ru.job4j.dao.modeldao;


import ru.job4j.dao.AbstractController;
import ru.job4j.model.Advert;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AdvertImpl extends AbstractController<Advert, Long> {
//    private static final Logger LOG = LogManager.getLogger(AdvertImpl.class);
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<Advert> getAll() {
//        return getCurrentSession().createQuery("from Advert ").list();
//    }
//
//    @Override
//    public Advert getEntityById(Long id) {
//        return getCurrentSession().get(Advert.class, id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        boolean flag = false;
//        Advert advert = getEntityById(id);
//        if (advert != null) {
//            flag = true;
//            getCurrentSession().delete(advert);
//        }
//        return flag;
//    }
//
//    @Override
//    public void delete(Advert entity) {
//        getCurrentSession().delete(entity);
//    }
//
//    @Override
//    public Long save(Advert entity) {
//        return (Long) getCurrentSession().save(entity);
//    }
//
//    @Override
//    public void update(Advert entity) {
//        getCurrentSession().update(entity);
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<Advert> getAdverts(long id) {
//        Query<Advert> query = getCurrentSession().createQuery("from Advert where user.id = :id");
//        query.setParameter("id", id); from Ad where user.id = :param
//        return query.list();
//    }
//    @SuppressWarnings("unchecked")
//    public List<Advert> getByQuery(String s) {
//        List<Advert> adverts;
//        adverts = (List<Advert>) getCurrentSession().createQuery(s).list();
//        return adverts;
//    }
//
//    public List<Advert> getByQueryWithJoin(String s) {
//        List<Advert> adverts;
//        Query query = getCurrentSession().createQuery(s);
//        List<Object> result = (List<Object>) query.list();
//        adverts = result.stream().map(o -> (Object[]) o).map(ob -> (Advert) ob[0]).collect(Collectors.toList());
//        return adverts;
//    }
}

package ru.job4j.repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import ru.job4j.model.Advert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.06.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AdvertSpec implements Specification<Advert> {
    private static final Logger LOG = LogManager.getLogger(AdvertSpec.class);

    private Map map;

    public AdvertSpec(Map map) {
        this.map = map;
    }

    @Override
    public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();


        return andTogether(predicates, criteriaBuilder);
    }


    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}

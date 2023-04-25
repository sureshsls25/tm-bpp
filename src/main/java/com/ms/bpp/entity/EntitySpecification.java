package com.ms.bpp.entity;

import com.ms.bpp.dao.BppDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySpecification<T> implements Specification<T> {
    private static final Logger logger = LoggerFactory.getLogger(EntitySpecification.class);
    private List<String> columns;
    private String search;

    public EntitySpecification(List<String> columns, String search) {
        this.columns = columns;
        this.search = search;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (String column : columns) {
            Path path = buildPath(column, root);
            if (search.contains(" ")) {
                List<String>serchWord = Arrays.stream(search.split("\\s+")).collect(Collectors.toList());
                serchWord.forEach(s -> {
                    Predicate predicate1 = criteriaBuilder.like(criteriaBuilder.upper(path), "%" + s.toUpperCase() + "%");
                    Predicate predicate2 = criteriaBuilder.like(criteriaBuilder.upper(path), s.toUpperCase() + "%");
                    predicates.add(predicate1);
                    predicates.add(predicate2);
                });
            } else {
                Predicate predicate1 = criteriaBuilder.like(criteriaBuilder.upper(path), "%" + search.toUpperCase() + "%");
                predicates.add(predicate1);
            }
        }
        return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
    }

    private Path buildPath(String column, Path path) {

        if (!column.contains(".")) return path.get(column);

        String[] parts = column.split("\\.");
        for (String part : parts) {
            path = path.get(part);
        }

        return path;
    }

}

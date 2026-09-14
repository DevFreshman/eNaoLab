package org.com.lab.specification;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class DynamicSpecificationBuilder {

    private DynamicSpecificationBuilder() {}

    public static <T> Specification<T> build(List<SearchCriteria> criteriaList) {
        return (root, query, cb) -> {
            if (criteriaList == null || criteriaList.isEmpty()) {
                return cb.conjunction();
            }
            Predicate[] predicates = criteriaList.stream()
                    .map(c -> toPredicate(root, cb, c))
                    .toArray(Predicate[]::new);
            return cb.and(predicates);
        };
    }

    private static Predicate toPredicate(Root<?> root, CriteriaBuilder cb, SearchCriteria c) {
        return switch (c.operator()) {
            case "eq" -> cb.equal(root.get(c.field()), c.value());
            case "like" -> cb.like(cb.lower(root.get(c.field())),
                    "%" + c.value().toString().toLowerCase() + "%");
            case "in" -> root.get(c.field()).in((List<?>) c.value());
            default -> throw new IllegalArgumentException("Unsupported operator: " + c.operator());
        };
    }
}
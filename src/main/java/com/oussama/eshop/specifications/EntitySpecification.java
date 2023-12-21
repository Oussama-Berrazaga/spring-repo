package com.oussama.eshop.specifications;

import com.oussama.eshop.controllers.requests.FindRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EntitySpecification<T> {

    public Specification<T> specificationBuilder(FindRequest findRequest) {
        if (Objects.nonNull(findRequest) && !CollectionUtils.isEmpty(findRequest.getFilters())) {
            List<FindRequest.Filter> filters = findRequest.getFilters();
            List<Specification<T>> specifications = filters.stream()
                    .map(this::createSpecification)
                    .collect(Collectors.toList());

            return Specification.allOf(specifications);
        }
        return null;
    }

    private Specification<T> createSpecification(FindRequest.Filter filter) {
        return switch (filter.getOperator()) {
            case EQUALS ->
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(filter.getField()), filter.getValue());
            case NOT_EQUALS ->
                    (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(filter.getField()), filter.getValue());
            case GREATER_THAN ->
                    (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getField()), filter.getValue());
            case LESS_THAN ->
                    (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(filter.getField()), filter.getValue());
            case LIKE ->
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(filter.getField()), "%" + filter.getValue() + "%");
            case IN ->
                    (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(filter.getField()))
                            .value(castToRequiredType(
                                    root.get(filter.getField()).getJavaType(),
                                    filter.getValues()));
        };
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

}
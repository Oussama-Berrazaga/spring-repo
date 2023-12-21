package com.oussama.eshop.controllers.requests;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FindRequest {

    private List<Filter> filters;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    public static class Filter {
        // Name of the operation we like to perform
        public enum QueryOperator {
            EQUALS, NOT_EQUALS, LIKE, LESS_THAN, GREATER_THAN,IN
        }

        private String field; // Name of the filed from entity like firstName
        private QueryOperator operator; // Operator we like to apply
        private String value; // value we would like to match
        private List<String> values;
    }
}
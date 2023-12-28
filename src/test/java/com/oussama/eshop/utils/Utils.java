package com.oussama.eshop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

public class Utils {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Value("${application.security.accounts.test.email:test@test.com}")
    public static String TEST_EMAIL;
    @Value("${application.security.accounts.test.password:password}")
    public static String TEST_PASSWORD;
    @Value("${application.security.accounts.test.firstname:test}")
    public static String TEST_FIRSTNAME;
    @Value("${application.security.accounts.test.lastname:test}")
    public static String TEST_LASTNAME;
}

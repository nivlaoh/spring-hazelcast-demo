package com.hokaicheng.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hokaicheng.model.PhoneNumber;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void marshall() {
        PhoneNumber number = new PhoneNumber("65", "1234567");
        assertEquals("{\"countryCode\":\"65\",\"number\":\"1234567\"}", StringUtil.marshall(number));
    }

    @Test
    public void unmarshallClass() {
        PhoneNumber result = StringUtil.unmarshall("{\"countryCode\":\"65\",\"number\":\"1234567\"}", PhoneNumber.class);
        assertEquals("65", result.getCountryCode());
        assertEquals("1234567", result.getNumber());
    }

    @Test
    public void shouldUnmarshallTypeReference() {
        Map<String, String> result = StringUtil.unmarshall("{\"countryCode\":\"65\",\"number\":\"1234567\"}", new TypeReference<Map<String, String>>() {});
        assertEquals("65", result.get("countryCode"));
        assertEquals("1234567", result.get("number"));
    }
}
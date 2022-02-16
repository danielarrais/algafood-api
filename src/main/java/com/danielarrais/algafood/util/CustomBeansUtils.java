package com.danielarrais.algafood.util;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomBeansUtils extends BeanUtils {
    public static <T> void copyNonNullValues(Object input, T output) {
        var ignoreProperties = nullPropertiesFromObject(input);
        copyProperties(input, output, ignoreProperties);
    }

    @SneakyThrows
    private static String[] nullPropertiesFromObject(Object object) {
        List<String> nullFields = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object attribute = field.get(object);
            if (attribute == null) {
                nullFields.add(field.getName());
            }
        }

        return nullFields.toArray(new String[0]);
    }

    public static <T> T getPropertieValue(Object value, String valorField) throws InvocationTargetException, IllegalAccessException {
        return (T) Objects.requireNonNull(getPropertyDescriptor(value.getClass(), valorField)).getReadMethod().invoke(value);
    }
}

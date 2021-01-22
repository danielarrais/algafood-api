package com.danielarrais.algafood.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomBeansUtils extends BeanUtils {
    public static <T> void copyNoNullValues(Object input, T output) {
        var ignoreProperties = nullPropertiesFromObject(input);
        copyProperties(input, output, ignoreProperties);
    }

    public static <T> void mergeValues(Map<String, Object> dadosOrigem, T destino) {
        var objectMapper = buildObjectMapper();
        var destinoClass = destino.getClass();
        var origemMapper = objectMapper.convertValue(dadosOrigem, destinoClass);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(destinoClass, nomePropriedade);
            Objects.requireNonNull(field).setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, origemMapper);

            ReflectionUtils.setField(field, destino, novoValor);
        });
    }

    @SneakyThrows
    private static String[] nullPropertiesFromObject(Object o) {
        List<String> nullFields = new ArrayList<>();

        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object attribute = field.get(o);
            if (attribute == null) {
                nullFields.add(field.getName());
            }
        }

        return nullFields.toArray(new String[0]);
    }

    private static ObjectMapper buildObjectMapper() {
        var objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        return objectMapper;
    }

    public static <T> T getPropertieValue(Object value, String valorField) throws InvocationTargetException, IllegalAccessException {
        return (T) Objects.requireNonNull(getPropertyDescriptor(value.getClass(), valorField)).getReadMethod().invoke(value);
    }
}

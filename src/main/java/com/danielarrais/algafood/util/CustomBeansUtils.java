package com.danielarrais.algafood.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

public class CustomBeansUtils extends BeanUtils {
    public static <T> void mergeValues(Map<String, Object> dadosOrigem, T destino) {
        var destinoClass = destino.getClass();
        var origemMapper = new ObjectMapper().convertValue(dadosOrigem, destinoClass);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(destinoClass, nomePropriedade);
            Objects.requireNonNull(field).setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, origemMapper);

            ReflectionUtils.setField(field, destino, novoValor);
        });
    }
}

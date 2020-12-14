package com.danielarrais.algafood.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

public class CustomBeansUtils extends BeanUtils {
    public static<T> void mergeValues(Map<String, Object> dadosOrigem, T destino) {
            ObjectMapper objectMapper = new ObjectMapper();
            var restauranteOrigem = objectMapper.convertValue(dadosOrigem, destino.getClass());

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(destino.getClass(), nomePropriedade);
                Objects.requireNonNull(field).setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, destino, novoValor);
            });
    }
}

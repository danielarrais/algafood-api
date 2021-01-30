package com.danielarrais.algafood.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperUtils {
    private static ModelMapper modelMapper;

    public ModelMapperUtils(ModelMapper modelMapper) {
        ModelMapperUtils.modelMapper = modelMapper;
    }

    public static <T> T mapper(Object input, Class<T> outputType) {
        return modelMapper.map(input, outputType);
    }

    public static <T> List<T> mapper(Collection<?> inputList, Class<T> outputType) {
        return inputList
                .stream().map(user -> modelMapper.map(user, outputType))
                .collect(Collectors.toList());
    }
}

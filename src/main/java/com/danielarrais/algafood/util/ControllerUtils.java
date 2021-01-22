package com.danielarrais.algafood.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ControllerUtils {
    private static ModelMapper modelMapper;

    public ControllerUtils(ModelMapper modelMapper) {
        ControllerUtils.modelMapper = modelMapper;
    }

    public static<T> T mapper(Object input, Class<T> outputType) {
        return modelMapper.map(input, outputType);
    }

    public static<T> List<T> mapper(List<?> inputList, Class<T> outputType) {
        return inputList
                .stream().map(user -> modelMapper.map(user, outputType))
                .collect(Collectors.toList());
    }
}

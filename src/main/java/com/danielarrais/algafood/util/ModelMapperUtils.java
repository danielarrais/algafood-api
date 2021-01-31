package com.danielarrais.algafood.util;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public static <T> Page<T> mapper(Page<?> inputList, Class<T> outputType) {
        var contentPage = mapper(inputList.getContent(), outputType);

        return new PageImpl<T>(contentPage, Pageable.unpaged(), inputList.getTotalElements());
    }

    public static <T> List<T> mapper(Collection<?> inputList, Class<T> outputType) {
        return inputList.stream()
                .map(user -> modelMapper.map(user, outputType))
                .collect(Collectors.toList());
    }


}

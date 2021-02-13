package com.danielarrais.algafood.core.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {
    private List<String> allowed;

    @Override
    public void initialize(FileContentType fileContentType) {
        this.allowed = Arrays.asList(fileContentType.allowed());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) || allowed.contains(value.getContentType())){
            return true;
        }

        return false;
    }
}

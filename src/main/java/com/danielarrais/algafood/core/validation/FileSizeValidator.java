package com.danielarrais.algafood.core.validation;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private DataSize maxSize;

    @Override
    public void initialize(FileSize fileSize) {
        this.maxSize = DataSize.parse(fileSize.max());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) || value.getSize() <= maxSize.toBytes()){
            return true;
        }

        return false;
    }
}

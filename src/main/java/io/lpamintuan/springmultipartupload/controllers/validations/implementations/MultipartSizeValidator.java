package io.lpamintuan.springmultipartupload.controllers.validations.implementations;

import java.io.IOException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import io.lpamintuan.springmultipartupload.controllers.validations.annotations.ValidateFileSize;

public class MultipartSizeValidator implements ConstraintValidator<ValidateFileSize, MultipartFile> {

    private long sizeLimit;

    @Override
    public void initialize(ValidateFileSize constraintAnnotation) {
        this.sizeLimit = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        try {
            byte[] fileBytes = value.getBytes();
            return fileBytes.length <= sizeLimit;
        } catch (IOException e) {
            throw new RuntimeException("Invalid file.");
        }
    }
    
}

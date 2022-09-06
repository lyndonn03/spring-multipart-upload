package io.lpamintuan.springmultipartupload.controllers.validations.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import io.lpamintuan.springmultipartupload.controllers.validations.implementations.MultipartSizeValidator;

@Documented
@Constraint(validatedBy = MultipartSizeValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateFileSize {

    long size() default 2097152;

    String message() default "File size exceeded.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}

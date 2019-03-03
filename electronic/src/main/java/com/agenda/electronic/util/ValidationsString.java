package com.agenda.electronic.util;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.DateTimeRangeValidator;
import com.vaadin.data.validator.EmailValidator;

import java.time.LocalDate;

public class ValidationsString {

    public static final boolean isEmptyOrNull(String value){

        boolean validation = false;

        validation = (value == null || value.isEmpty()) ? true : false;

        return validation;
    }

    public static final boolean onlyString(String value) {

        boolean validation = false;
        if (value != null && !value.isEmpty()) {
            if (value.contains("0") ||
                    value.contains("1") ||
                    value.contains("2") ||
                    value.contains("3") ||
                    value.contains("4") ||
                    value.contains("5") ||
                    value.contains("6") ||
                    value.contains("7") ||
                    value.contains("8") ||
                    value.contains("9")) {

                validation = true;
            }
        }
        return validation;
    }

    public static final boolean onlyNumbers(String value) {
        boolean validation = false;

        if (value != null && !value.isEmpty()) {
            try {
                Integer.parseInt(value);
                validation = false;
            } catch (Exception e) {
                validation = true;
            }
        }
        return validation;
    }

    public static final boolean validEmail(String value) {

        boolean validation = false;
        if (value != null && !value.isEmpty()) {
            Validator emailValidator = new EmailValidator("Email no valido, verificar el formato");
            ValidationResult validationResult = emailValidator.apply(value, new ValueContext());
            validation = validationResult.isError();
        }
        return validation;
    }
}

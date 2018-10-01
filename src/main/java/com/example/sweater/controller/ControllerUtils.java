package com.example.sweater.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.10.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ControllerUtils {

    /**
     * Преобразуем лист с ошибками в мап с ошибками
     * @param bindingResult  список аргументов и сообщений ошибок валидации
     * @return map ключ имя филда + Error (например textError tegError) значение FieldError.getDefaultMessage()
     */
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}

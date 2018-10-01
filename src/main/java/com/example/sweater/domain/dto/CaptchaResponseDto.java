package com.example.sweater.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.10.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto {

    private  boolean success;

    @JsonAlias("error-codes")//алиас так как java не поддерживает поля через дефиз
    private Set<String> errorCodes;
}

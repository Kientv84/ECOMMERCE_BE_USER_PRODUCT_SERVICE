package com.ecommerce.kientv84.exceptions;

import com.ecommerce.kientv84.commons.EnumError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@AllArgsConstructor
public class ServiceException extends  RuntimeException {
    private final String errorCode;
    private final String messageCode; // key trong messages.properties
    private final HttpStatus httpStatus;
    private final Object[] args;

    public ServiceException(EnumError error) {
        this(error, error.name().toLowerCase(), null);
        // ví dụ: "d001" => messages.properties phải có key "d001"
    }

    public ServiceException(EnumError error, String messageCode) {
        this(error, messageCode, null);
    }

    public ServiceException(EnumError error, String messageCode, Object[] args) {
        super(error.getDefaultMessage());
        this.errorCode = error.getCode();
        this.messageCode = messageCode;
        this.httpStatus = error.getHttpStatus();
        this.args = args;
    }
}

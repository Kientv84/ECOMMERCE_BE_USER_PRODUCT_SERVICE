package com.ecommerce.kientv84.exceptions;

import com.ecommerce.kientv84.commons.EnumError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    private final EnumError enumError;     // ✅ giữ EnumError gốc (rất quan trọng)
    private final String errorCode;        // mã lỗi ví dụ "ACC-D-B001"
    private final String messageCode;      // key để lấy trong messages.properties
    private final HttpStatus httpStatus;   // HTTP status tương ứng
    private final Object[] params;         // tham số để format message động

    public ServiceException(EnumError error) {
        this(error, error.name().toLowerCase(), null);
    }

    public ServiceException(EnumError error, String messageCode) {
        this(error, messageCode, null);
    }

    public ServiceException(EnumError error, String messageCode, Object[] params) {
        super(error.getDefaultMessage());
        this.enumError = error;
        this.errorCode = error.getCode();
        this.messageCode = messageCode;
        this.httpStatus = error.getHttpStatus();
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }
}

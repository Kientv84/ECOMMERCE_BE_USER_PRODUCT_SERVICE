package com.ecommerce.kientv84.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ServiceException {
    private final String errorCode;
    private final String messageCode; // key trong messages.properties
    private final HttpStatus httpStatus;
    private final Object[] args;
//
//    public DispatchServiceException(DispatchError error) {
//        this(error, error.name().toLowerCase(), null);
//        // ví dụ: "d001" => messages.properties phải có key "d001"
//    }

//    public DispatchServiceException(DispatchError error, String messageCode) {
//        this(error, messageCode, null);
//    }
//
//    public DispatchServiceException(DispatchError error, String messageCode, Object[] args) {
//        super(error.getDefaultMessage());
//        this.errorCode = error.getCode();
//        this.messageCode = messageCode;
//        this.httpStatus = error.getHttpStatus();
//        this.args = args;
//    }
//}

}

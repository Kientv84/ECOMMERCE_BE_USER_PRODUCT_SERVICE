package com.ecommerce.kientv84.commons;

import com.ecommerce.kientv84.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum EnumError {

    // ========== Validation ==========
    ACC_U_V001("ACC-U-V001", "Invalid email format", HttpStatus.BAD_REQUEST),
    ACC_U_V002("ACC-U-V002", "Password too short", HttpStatus.BAD_REQUEST),

    // ========== Business ==========
    ACC_D_B001("ACC-D-B001", "No vehicles available in Redis", HttpStatus.NOT_FOUND),
    ACC_D_B002("ACC-D-B002", "No vehicle matched type", HttpStatus.BAD_REQUEST),
    ACC_D_B003("ACC-D-B003", "Driver not accepted booking", HttpStatus.BAD_REQUEST),

    // ========== System ==========
    ACC_S_S001("ACC-S-S001", "Redis connection failed", HttpStatus.INTERNAL_SERVER_ERROR),
    ACC_S_S002("ACC-S-S002", "Kafka publish error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;


    public static EnumError fromCode(String code) {
        for (EnumError e : values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unknown DispatchError code: " + code);
    }
}

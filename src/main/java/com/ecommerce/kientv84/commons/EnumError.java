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
    // lỗi 400: Lỗi logic BAD_REQUEST lõi validate
    // Lỗi 401: NOT_FOUND hoặc SERVICE_UNAVAILABLE ( cho system vd redis )
    // lỗi 500: INTERNAL_SERVER_ERROR lỗi hệ thống
    // lỗi 409: Lỗi conflict CONFLICT

    // ========== Validation ==========
//    ACC_U_V001("ACC-U-V001", "Invalid email format", HttpStatus.BAD_REQUEST),
//    ACC_U_V002("ACC-U-V002", "Password too short", HttpStatus.BAD_REQUEST),

    // ========== Business ==========
//    ACC_D_B001("ACC-D-B001", "No vehicles available in Redis", HttpStatus.NOT_FOUND),
//    ACC_D_B002("ACC-D-B002", "No vehicle matched type", HttpStatus.BAD_REQUEST),
//    ACC_D_B003("ACC-D-B003", "Driver not accepted booking", HttpStatus.BAD_REQUEST),

    // ========== System ==========
//    ACC_S_S001("ACC-S-S001", "Redis connection failed", HttpStatus.INTERNAL_SERVER_ERROR),
//    ACC_S_S002("ACC-S-S002", "Kafka publish error", HttpStatus.INTERNAL_SERVER_ERROR),

//----------- ACCOUNT ------------
    ACC_DATA_EXISTED("ACC-DTE", "Data exit", HttpStatus.CONFLICT),

    //get

    ACC_ERR_GET("ACC-GA", "Have error in process get", HttpStatus.BAD_REQUEST),

    // update
    ACC_ERR_UPD("ACC-UD", "Have error in process update account", HttpStatus.BAD_REQUEST),

    //Delete
    ACC_ERR_DEL("ACC-DL", "Have error in process delete", HttpStatus.BAD_REQUEST),
    ACC_ERR_DEL_EM("ACC-DL-E", "List ids to delete is empty", HttpStatus.BAD_REQUEST),
    ACC_ERR_NOT_FOUND("ACC_EL_NF", "Not found user with id", HttpStatus.BAD_REQUEST),

    //----------- PRODUCT ------------
    PRO_ERR_GET("PRO-GA", "Have error in process get", HttpStatus.BAD_REQUEST),

    PRO_ERR_DEL("PRO-DL", "Have error in process delete", HttpStatus.BAD_REQUEST),
    PRO_ERR_DEL_EM("PRO-DL-E", "List ids to delete is empty", HttpStatus.BAD_REQUEST),
    PRO_ERR_NOT_FOUND("PRO_EL_NF", "Not found user with id", HttpStatus.BAD_REQUEST),


    //----------- CATEGORY ------------
    CATE_ERR_GET("CATE-GA", "Have error in process get", HttpStatus.BAD_REQUEST),
    CATE_ERR_DEL_EM("CATE-DL-E", "List ids to delete is empty", HttpStatus.BAD_REQUEST),
    CATE_DATA_EXISTED("CATE-DTE", "Data exit", HttpStatus.CONFLICT),

    INTERNAL_ERROR("ACC-S-999", "Unexpected internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
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

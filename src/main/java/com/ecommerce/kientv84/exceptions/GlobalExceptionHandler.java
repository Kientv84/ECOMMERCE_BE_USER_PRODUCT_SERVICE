package com.ecommerce.kientv84.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;
    
    /**
     * Xử lý lỗi validate (@Valid, @NotNull...)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String localizedErrorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fieldErrors.put(error.getField(), localizedErrorMessage);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("details", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Xử lý các lỗi custom từ DispatchServiceException
     * (Có hỗ trợ message từ messageSource dựa trên messageCode)
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, Object>> handleDispatchServiceException(ServiceException ex) {
        Locale locale = LocaleContextHolder.getLocale();

        // Lấy message từ messageSource dựa vào messageCode
        String localizedMessage = messageSource.getMessage(
                ex.getMessageCode(),
                null,
                locale
        );

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errorCode", ex.getErrorCode());
        response.put("message", localizedMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Xử lý tất cả các exception còn lại
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultMessage = messageSource.getMessage( "D006", // fallback "Error while processing dispatch"
                null,
                "Unexpected error occurred",
                locale
        );

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("message", defaultMessage + " | " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

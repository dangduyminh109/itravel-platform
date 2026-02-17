package com.itravel.platform.common.exceptions;

import com.itravel.platform.common.dto.ApiError;
import com.itravel.platform.common.dto.ApiResponse;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<Void>> ExceptionHandler(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }

    @ExceptionHandler(value = DomainException.class)
    ResponseEntity<ApiResponse<Void>> AppExceptionHandler(DomainException e) {
        DomainErrorCode errorCode = e.getDomainErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getFeild())
                                        .build()
                        ))
                        .build()
        );
    }

    @ExceptionHandler(value = ApplicationException.class)
    ResponseEntity<ApiResponse<Void>> AppExceptionHandler(ApplicationException e) {
        ApplicationErrorCode errorCode = e.getApplicationErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getFeild())
                                        .build()
                        ))
                        .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_FORM_FORMAT;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Void>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();

            if ("typeMismatch".equals(fieldError.getCode())
                    || (fieldError.getCodes() != null && Arrays.stream(fieldError.getCodes()).anyMatch(code -> code.contains("typeMismatch")))) {
               if (fieldName.contains("image")) {
                    errorCode = ErrorCode.INVALID_FILE;
               } else {
                   errorCode = ErrorCode.INVALID_TYPE_DATA;
               }
            } else {
                try {
                    errorCode = ErrorCode.valueOf(fieldError.getDefaultMessage());
                } catch (IllegalArgumentException ex) {
                    log.error("ErrorCode {} does not exist in enum\n{}", fieldError.getDefaultMessage(), ex.getMessage());
                    errorCode = ErrorCode.INVALID_KEY;
                }
            }
            break;
        }

        if (e.getBindingResult().getFieldErrors().isEmpty() && !e.getBindingResult().getGlobalErrors().isEmpty()) {
            String defaultMessage = e.getBindingResult().getGlobalErrors().get(0).getDefaultMessage();
            try {
                errorCode = ErrorCode.valueOf(defaultMessage);
            } catch (IllegalArgumentException ex) {
                log.error("Global ErrorCode {} does not exist in enum\n{}", defaultMessage, ex.getMessage());
                errorCode = ErrorCode.INVALID_KEY;
            }
        }

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }


    // token hết hạn
    @ExceptionHandler(value = AuthenticationServiceException.class)
    ResponseEntity<ApiResponse<Void>> AuthenticationServiceExceptionHandler(AuthenticationServiceException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }

    // lỗi content-type
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    ResponseEntity<ApiResponse<Void>> HttpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        ErrorCode errorCode = ErrorCode.UNSUPPORTED_MEDIA_TYPE;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }

    // không có quyền truy cập
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<Void>> AccessDeniedExceptionHandler(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                  ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }

    // không có quyền truy cập
    @ExceptionHandler(value = JwtException.class)
    ResponseEntity<ApiResponse<Void>> JwtExceptionHandler(JwtException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                  ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }

    // lỗi giải token
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ApiResponse<Void>> ParseExceptionHandler(ParseException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                  ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }

    // sai key hoặt thuật toán
    @ExceptionHandler(JOSEException.class)
    public ResponseEntity<ApiResponse<Void>> JOSEExceptionHandler(JOSEException ex) {
        ErrorCode errorCode = ErrorCode.CANNOT_CREATE_TOKEN;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                  ApiResponse.<Void>builder()
                        .message(errorCode.getMessage())
                        .success(false)
                        .Errors(List.of(
                                ApiError.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .field(errorCode.getField())
                                        .build()
                        ))
                        .build()
        );
    }
}

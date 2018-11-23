/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.vdp.core.common.exception;

import com.unisinsight.framework.common.exception.BaseErrorCode;
import com.unisinsight.framework.common.exception.BaseException;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * description 统一异常处理
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(final Exception ex, final WebRequest req) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR ;

        ErrorResult result = new ErrorResult();
        result.setServerTime(new Date());
        result.setRequestId(UUID.randomUUID().toString());

        if (ex instanceof BaseException ) {
            result.setErrorCode(((BaseException) ex).getErrorCode());
            result.setMessage(ex.getMessage());
        } else if (ex instanceof MethodArgumentNotValidException) {
            List<FieldError> errors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            StringBuilder messageBuilder = new StringBuilder();
            String message;
            for (int i = 0; i < errors.size(); i++) {
                FieldError error = errors.get(i);
                message = Strings.isNotBlank(error.getDefaultMessage())? error.getDefaultMessage():"参数异常";
                messageBuilder.append(error.getField()).append(message);
                if ( i < errors.size() - 1) {
                    messageBuilder.append(";");
                }
            }
            httpStatus = HttpStatus.BAD_REQUEST ;
            result.setErrorCode(BaseErrorCode.INVALID_PARAM_ERROR.getErrorCode());
            result.setMessage(messageBuilder.toString());
        } else if (ex instanceof HttpMessageNotReadableException) {
            result.setErrorCode(BaseErrorCode.DATA_FORMAT_ERROR.getErrorCode());
            result.setMessage("请求参数格式错误");
        } else  {
            result.setErrorCode(BaseErrorCode.UNKNOWN_ERROR.getErrorCode());
            result.setMessage(BaseErrorCode.UNKNOWN_ERROR.getMessage());
        }
        logger.error("异常堆栈:",ex);
        return new ResponseEntity<>(result,httpStatus);
    }
    
    static class ErrorResult {
        private String errorCode;
        private String message;
        private Date serverTime;
        private String requestId;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Date getServerTime() {
            return serverTime;
        }

        public void setServerTime(Date serverTime) {
            this.serverTime = serverTime;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }
    }
}
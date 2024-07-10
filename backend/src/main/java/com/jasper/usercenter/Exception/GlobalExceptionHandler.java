package com.jasper.usercenter.Exception;

import com.jasper.usercenter.common.BaseResponse;
import com.jasper.usercenter.common.ErrorCode;
import com.jasper.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
/**
 * Global exception handler
 * @Author Jie Liu
 */
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionException(BusinessException businessException) {
        log.error("Business exception: ", businessException.getMessage(), businessException);
        return ResultUtils.error(businessException.getCode(), businessException.getMessage(), businessException.getDescription());
    }


    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionException(RuntimeException runtimeException) {
        log.error("Runtime exception: ", runtimeException);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, runtimeException.getMessage(), "");
    }
}

package com.jasper.usercenter.Exception;


import com.jasper.usercenter.common.ErrorCode;

/**
 * Business exception
 * @Author Jie Liu
 */
public class BusinessException extends RuntimeException {

    private final int code;

    private final String description;

    public BusinessException(int code, String description, String message) {
        super(message);
        this.code = code;
        this.description =description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }


    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}

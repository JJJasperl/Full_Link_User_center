package com.jasper.usercenter.common;

/**
 * Error code
 * @author Jie Liu
 */
public enum ErrorCode {


    SUCCESS(0, "Success", ""),
    PARAMS_ERROR(40000, "Parameter error", ""),
    NULL_ERROR(40001, "Parameter is null", ""),
    NOT_LOGIN(40100, "Not logged in", ""),
    NO_AUTH(40101, "No permission", ""),
    SYSTEM_ERROR(50000, "Server error", ""),;


    private final int code;

    private final String message;

    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

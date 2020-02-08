package com.zhang.comunity.exception;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 11:30
 */
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

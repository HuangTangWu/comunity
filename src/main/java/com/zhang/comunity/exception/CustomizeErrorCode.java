package com.zhang.comunity.exception;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 14:11
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("您的问题找不到了,要不换个试试？");

    private String message;


    CustomizeErrorCode(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

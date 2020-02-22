package com.zhang.comunity.exception;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/8 14:11
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"您的问题找不到了,要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"您的评论找不到了,要不换个试试？"),
    NO_LOGIN(2003,"当前用户未登录,请登陆后再评论"),
    SYS_ERROR(2004,"服务器冒烟了,请稍后重试"),
    TYPE_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论消失了,换一个把！"),
    CONTENT_EMPTY(2007,"评论的内容为空！");
    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

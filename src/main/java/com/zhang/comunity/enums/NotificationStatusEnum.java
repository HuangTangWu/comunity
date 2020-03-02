package com.zhang.comunity.enums;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/28 16:17
 */
public enum NotificationStatusEnum {
    READ(0),UNREAD(1);
    private int status;

    public int getStatus() {
        return status;
    }
    NotificationStatusEnum(int status){
        this.status=status;
    }
}

package com.zhang.comunity.enums;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/28 16:20
 */
public enum NotificationTypeEnum {
    NOTIFY_QUESTION(0,"回复了问题"),
    NOTIFY_COMMENT(1,"回复了评论");
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}

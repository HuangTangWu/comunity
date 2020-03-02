package com.zhang.comunity.entity;

import lombok.Data;


/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/25 14:52
 */
@Data
public class Notification {
    private Long id;
    private Long notifier;
    private String notifierName;
    private Long receiver;
    private Long outerId;
    private String outerTitle;
    private int type;
    private Long gmt_create;
    private int status;
}

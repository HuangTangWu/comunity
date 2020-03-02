package com.zhang.comunity.mapper;

import com.zhang.comunity.entity.Notification;
import com.zhang.comunity.enums.NotificationStatusEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/25 14:59
 */
@Mapper
public interface NotificationMapper {
    int addNotification(Notification notification);

    List<Notification> selectNotifyByReceiver(Long receiver);

    int countNotifyByReceiver(Long receiver);

    Notification getNotifyById(Long id);

    void updateNotification(Notification notification);

    int countNotifyByStatus(Map map);
}

package com.zhang.comunity.service;

import com.github.pagehelper.PageInfo;
import com.zhang.comunity.dto.Pagination;
import com.zhang.comunity.entity.Notification;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.enums.NotificationStatusEnum;
import com.zhang.comunity.exception.CustomizeErrorCode;
import com.zhang.comunity.exception.CustomizeException;
import com.zhang.comunity.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/28 17:10
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    public List<Notification> getNotify(Long receiver){
        return notificationMapper.selectNotifyByReceiver(receiver);
    }

    public int countNotify(Long receiver){
        return notificationMapper.countNotifyByReceiver(receiver);
    }


    /**
     * 处理分页
     */
    public Pagination getPages(Integer totalCount, Integer pageNum, Integer pagesize, PageInfo<?> DTOList){
        Pagination pagination = new Pagination();
        pagination.setPagination(totalCount,pageNum,pagesize);
        pagination.setDTOList(DTOList);
        return pagination;
    }

    public Notification read(Long id) {
        Notification notification=notificationMapper.getNotifyById(id);
        if(notification==null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFY_NOT_FOUND);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        return notification;
    }

    public void updateNotify(Notification notification) {
        notificationMapper.updateNotification(notification);
    }

    public int countNotifyByStatus(int unread, Integer id) {
        Map map=new HashMap();
        map.put("unRead",unread);
        map.put("userId",id);
        return notificationMapper.countNotifyByStatus(map);
    }
}

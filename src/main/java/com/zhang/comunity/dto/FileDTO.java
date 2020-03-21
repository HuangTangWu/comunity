package com.zhang.comunity.dto;

import com.zhang.comunity.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/3/17 17:29
 */
@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;

    public static FileDTO okOf(String url){
        FileDTO fileDTO=new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("上传图片成功！");
        fileDTO.setUrl(url);
        return fileDTO;
    }

    public static FileDTO errorOf(){
        FileDTO fileDTO=new FileDTO();
        fileDTO.setSuccess(0);
        fileDTO.setMessage(CustomizeErrorCode.UPLOAD_FAIL.getMessage());
        return fileDTO;
    }
}

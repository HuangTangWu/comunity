package com.zhang.comunity.dto;

import com.zhang.comunity.exception.CustomizeErrorCode;
import com.zhang.comunity.exception.CustomizeException;
import lombok.Data;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/9 14:54
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;
    public static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.code=customizeErrorCode.getCode();
        resultDTO.message=customizeErrorCode.getMessage();
        return  resultDTO;
    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeException e){
        return errorOf(e.getCode(),e.getMessage());
    }

    private static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

}

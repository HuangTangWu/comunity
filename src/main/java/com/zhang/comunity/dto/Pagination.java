package com.zhang.comunity.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/17 11:29
 */
@Data
public class Pagination {
    private PageInfo<QuestionDTO> questionDTOList;
    private Boolean showPreviews;
    private Boolean showFirstPage;
    private Boolean showEndPage;
    private Boolean showNextPage;
    private Integer page; //当前页
    private Integer totalPage;//总页数
    private List<Integer> pages=new ArrayList<>();

    public  void setPagination(Integer totalCount,Integer page,Integer size){

        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        if(page>totalPage){
            page=totalPage;
        }
        if (page<1){
            page=1;
        }
        this.page=page;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }

            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //是否展示上一页
        if(page==1){
           showPreviews=false;
        }else {
            showPreviews=true;
        }

        if(page==totalPage){
            showNextPage=false;
        }else{
            showNextPage=true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }

        if(pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }




    }





}

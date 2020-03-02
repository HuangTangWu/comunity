package com.zhang.comunity.cache;

import com.zhang.comunity.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2020/2/23 16:02
 */
public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOList=new ArrayList<>();
        TagDTO language = new TagDTO();
        language.setCategoryName("开发语言");
        language.setTags(Arrays.asList("java","c","c++","php","perl","python","javascript","c#","ruby","go","lua","node.js","erlang","scala","bash","actionscript","golang","typescript","flutter"));
        tagDTOList.add(language);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("vim","emacs","idea","eclipse","xcode","intellij-idea","textmate","sublime-text","visual-studio","git","github","svn","hg","docker","ci"));
        tagDTOList.add(tool);

        TagDTO service = new TagDTO();
        service.setCategoryName("操作系统");
        service.setTags(Arrays.asList("linux","unix","ubuntu","windows-server","centos","负载均衡","缓存","apache","nginx"));
        tagDTOList.add(service);


        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("数据库","mysql","sqlite","nosql","mongodb","memcached","postgresql","redis"));
        tagDTOList.add(db);

        return tagDTOList;

    }
}

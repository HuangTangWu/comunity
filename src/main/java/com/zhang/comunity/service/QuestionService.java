package com.zhang.comunity.service;

import com.github.pagehelper.PageInfo;
import com.zhang.comunity.dto.Pagination;
import com.zhang.comunity.dto.QuestionDTO;
import com.zhang.comunity.dto.UserQuestionDTO;
import com.zhang.comunity.entity.Question;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.exception.CustomizeErrorCode;
import com.zhang.comunity.exception.CustomizeException;
import com.zhang.comunity.exception.ICustomizeErrorCode;
import com.zhang.comunity.mapper.QuestionMapper;
import com.zhang.comunity.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/16 12:20
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> getQuestion(){
        List<Question> questionsList=questionMapper.getAllQuestion();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question :
                questionsList) {
            User u=userMapper.getUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(u);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public UserQuestionDTO getMyQuestion(Integer userID){
        return questionMapper.getMyQuestion(userID);

    }

    public List<Question> getMyQuestions(Integer userID){
        return questionMapper.getMyQuestions(userID);

    }

    public int countMyQuestion(Integer userId){
        return questionMapper.countMyQuestion(userId);
    }

    public int countQuestion(){
        return questionMapper.countQuestion();
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

    public QuestionDTO getQuestionById(Integer id) {
        Question question=questionMapper.getQuestionById(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        User u=userMapper.getUserById(question.getCreator());
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(u);
        return  questionDTO;
    }

    public void createOrUpdate(Question question){
        if(question.getId()==null){
            //新建
            question.setComment_count(0);
            question.setLike_count(0);
            question.setView_count(0);
            questionMapper.addQuestion(question);
        }else {
            //更新
            int updated=questionMapper.updateQuestion(question);
            if(updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id){
        questionMapper.incView(id);
    }

    public void incCommentCount(Integer id){
        questionMapper.incCommentCount(id);
    }

    //获取相关问题
    public List<Question> getRelatedQuestionByTag(QuestionDTO questionDTO) {
        StringBuilder builder=new StringBuilder();
        for (String s : questionDTO.getTag().split(",")) {
            builder.append(s).append("|");
        }
        String tag = builder.toString().substring(0,builder.length()-1);
        Map<String,String> map=new HashMap<>();
        map.put("id",String.valueOf(questionDTO.getId()));
        map.put("tag",tag);
        List<Question> questionList=questionMapper.getRelatedQuestionByTag(map);
        return questionList;
    }
}

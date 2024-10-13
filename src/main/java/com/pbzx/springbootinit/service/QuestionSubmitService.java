package com.pbzx.springbootinit.service;

import com.pbzx.springbootinit.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.pbzx.springbootinit.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pbzx.springbootinit.model.entity.User;

/**
* @author wangyan
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-10-08 10:26:51
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交信息
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
}

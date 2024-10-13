package com.pbzx.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pbzx.springbootinit.common.ErrorCode;
import com.pbzx.springbootinit.exception.BusinessException;
import com.pbzx.springbootinit.mapper.QuestionSubmitMapper;
import com.pbzx.springbootinit.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.pbzx.springbootinit.model.entity.Question;
import com.pbzx.springbootinit.model.entity.QuestionSubmit;
import com.pbzx.springbootinit.model.entity.User;
import com.pbzx.springbootinit.model.enums.QuestionSubmitLanguageEnum;
import com.pbzx.springbootinit.model.enums.QuestionSubmitStatusEnum;
import com.pbzx.springbootinit.service.QuestionService;
import com.pbzx.springbootinit.service.QuestionSubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangyan
 * @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
 * @createDate 2024-10-08 10:26:51
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //校验语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "提交语言不合法");
        }
        long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        //设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据插入失败");
        }
        return questionSubmit.getId();
    }
}





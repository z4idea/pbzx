package com.pbzx.springbootinit.model.dto.question;

import lombok.Data;

/**
 * 判断题用例
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;
    /**
     * 输出用例
     */
    private String output;
}

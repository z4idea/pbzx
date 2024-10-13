package com.pbzx.springbootinit.model.dto.question;

import lombok.Data;

/**
 * 判断题用例
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制（ms）
     */
    private Long timeLimit;
    /**
     * 内存限制限制（KB）
     */
    private Long memoryLimit;
    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;
}

package com.vic.ck.console.config.lookup;

import com.vic.base.pager.CommonLookup;

/**
 * 封装提示音管理查询条件
 */
public class PromptLookup extends CommonLookup {
    /**
     * 提示音类型
     */
    private Integer prompt_type;

    public Integer getPrompt_type() {
        return prompt_type;
    }

    public void setPrompt_type(Integer prompt_type) {
        this.prompt_type = prompt_type;
    }
}

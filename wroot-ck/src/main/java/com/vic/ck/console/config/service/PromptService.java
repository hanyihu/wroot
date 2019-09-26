package com.vic.ck.console.config.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.config.mapper.PromptMapper;
import com.vic.ck.entity.BasicConfig;
import com.vic.wroot.common.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.swing.plaf.basic.BasicIconFactory;
import java.util.List;

@Service
public class PromptService extends BaseService {
    @Resource
    private PromptMapper promptMapper;
    @Resource
    private AttachmentService attachmentService;

    /**
     * 查询提示音列表
     * @param lookup 查询条件
     * @return 提示音数据及页面信息
     */
    public PageInfo<BasicConfig> list(Lookup lookup){
        startPage(lookup);
        List<BasicConfig> datas = promptMapper.list(lookup);
        return PageInfo.instance(datas,lookup);
    }

    /**
     * 根据id查询提示音信息
     * @param id
     * @return 提示音对象
     */
    public BasicConfig getConfigById(int id){
        return promptMapper.findById(id);
    }

    /**
     * 新增提示音
     * @param config 提示音对象
     * @return
     */
    @Transactional
    public int insert(BasicConfig config){
        attachmentService.updateTemporary(false, config.getPrompt_attachId());
        return promptMapper.insert(config);
    }

    /**
     * 修改提示音
     * @param config
     * @return
     */
    @Transactional
    public int update(BasicConfig config){
        BasicConfig old = getConfigById(config.getId());
        if(old.getPrompt_attachId() != config.getPrompt_attachId()){
            attachmentService.updateTemporary(true, old.getPrompt_attachId());
            attachmentService.updateTemporary(false, config.getPrompt_attachId());
        }
        return promptMapper.update(config);
    }

    /***
     * 删除
     */
    public int delete(int... ids) {
        for(int id :ids){//暂时for循环写
            BasicConfig old = getConfigById(id);
            attachmentService.updateTemporary(true, old.getPrompt_attachId());
        }
        return promptMapper.delete(ids);
    }
}

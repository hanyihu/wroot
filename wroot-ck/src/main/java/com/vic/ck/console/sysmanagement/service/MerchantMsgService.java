package com.vic.ck.console.sysmanagement.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.sysmanagement.mapper.MerchantMsgMapper;
import com.vic.ck.entity.MerchantMsg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MerchantMsgService extends BaseService {
    @Resource
    private MerchantMsgMapper merchantMsgMapper;
    /**
     * 查询商家留言列表
     * @param lookup
     * @return
     */
    public PageInfo<MerchantMsg> list(Lookup lookup){
        startPage(lookup);
        List<MerchantMsg> datas = merchantMsgMapper.list(lookup);
        return PageInfo.instance(datas,lookup);
    }

    /**
     * 根据主键查询留言管理对象
     * @param id
     * @return
     */
    public MerchantMsg findById(int id){
        return merchantMsgMapper.findById(id);
    }

    /**
     * 修改商家留言管理
     * @param merchantMsg
     * @return
     */
    public int update(MerchantMsg merchantMsg){
        return merchantMsgMapper.update(merchantMsg);
    }
}

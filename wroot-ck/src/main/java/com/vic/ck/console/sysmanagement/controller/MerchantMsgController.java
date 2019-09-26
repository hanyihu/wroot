package com.vic.ck.console.sysmanagement.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.sysmanagement.lookup.MerchantMsgLookup;
import com.vic.ck.console.sysmanagement.service.MerchantMsgService;
import com.vic.ck.entity.MerchantMsg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/console/sysmanagement/merchantMsg")
public class MerchantMsgController extends BaseConsoleController {
    @Resource
    private MerchantMsgService merchantMsgService;

    /**
     * 跳转到留言管理列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        PageInfo<MerchantMsg> pager = merchantMsgService.list(getLookup());
        model.addAttribute("pager",pager);
        return "/console/sysmanagement/merchantMsg/list";
    }

    /**
     * 重写查询条件
     * @return
     */
    protected Lookup instanceLookup(){
        return new MerchantMsgLookup();
    }

    /**
     * 查询
     * @param lookup 查询条件
     * @return 重定向到公告管理列表页
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(MerchantMsgLookup lookup){
        setLookup(lookup);
        return "redirect:/console/sysmanagement/merchantMsg/";
    }

    /**
     * 跳转到编辑评价管理页面
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id")Integer id, Model model){
        MerchantMsg merchantMsg = merchantMsgService.findById(id);
        model.addAttribute("merchantMsg",merchantMsg);
        return "/console/sysmanagement/merchantMsg/updateMerchantMsg";
    }

    /**
     * 修改商家留言管理信息
     * @param id
     * @param merchantMsg
     * @param attr
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id")Integer id, MerchantMsg merchantMsg, RedirectAttributes attr){
        merchantMsg.setId(id);
        if(merchantMsg.getStatus() == null || "".equals(merchantMsg.getStatus())){
            merchantMsg.setStatus(1);
        }
        merchantMsgService.update(merchantMsg);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/sysmanagement/merchantMsg/";
    }


}

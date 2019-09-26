package com.vic.ck.console.examine.controller;

import com.alibaba.fastjson.JSON;
import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.examine.service.ExamineService;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.RiderExamine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 开通骑手提交信息审核
 * @author hanyihu
 * @date 2019/4/23 11:02
 */
@Controller
@RequestMapping(value = "/console/rider/examine")
public class ExamineController extends BaseConsoleController {

    @Resource
    private ExamineService examineService;

    private Logger logger= LoggerFactory.getLogger(ExamineController.class);


    /**列表页*/
    @RequestMapping(value = "/riderInfor/", method = RequestMethod.GET)
    public String page(Model model) {

        PageInfo<RiderExamine> pager = examineService.list(getLookup());
        logger.info("获取的骑手信息===={}", JSON.toJSON(pager));

        logger.info("获取的骑手健康证信息===={}",pager.getDatas() );
        model.addAttribute("pager",pager);

        return "console/examine/riderInfor/list";
    }

    /*查看健康证图片*/
    @RequestMapping(value = "/riderInfor/{id}/lookHealthImage", method = RequestMethod.GET)
    public String lookHealthImage(@PathVariable int id, Model model) {

        RiderExamine cu = examineService.findById(id);
       model.addAttribute("healthList",cu.getHealthCard().split(","));
        return "console/examine/riderInfor/lookHealthImage";

    }

    /*查看身份证图片*/
    @RequestMapping(value = "/riderInfor/{id}/lookIdCardImage", method = RequestMethod.GET)
    public String lookIdCardImage(@PathVariable int id, Model model) {

        RiderExamine cu = examineService.findById(id);
        model.addAttribute("idList", cu.getIdCard().split(","));
        return "console/examine/riderInfor/lookIdCardImage";

    }


    /**查询*/
    @RequestMapping(value = "/riderInfor/", method = RequestMethod.POST)
    public String search(CommonLookup lookup) {
        setLookup(lookup);
        return "redirect:/console/rider/examine/riderInfor/";
    }

    /*通过审核*/
    @RequestMapping(value = "/riderInfor/{id}/passExamine", method = RequestMethod.GET)
    public String passExamine(@PathVariable int id, RedirectAttributes attributes) {
        //通过审核
        RiderExamine riderExamine = examineService.findById(id);
        Customer entity = new Customer();

        entity.setId(riderExamine.getRiderId());
        entity.setIsRider(1);//变成骑手
        entity.setSendCity(riderExamine.getSendcity());
        entity.setSendArea(riderExamine.getAddress());
        entity.setTtlAccounts(riderExamine.getPhone());
        entity.setHealthCard(riderExamine.getHealthCard());
        entity.setHealthCardNo(riderExamine.getHealthCardNo());
        entity.setHealthValidity(riderExamine.getHealthValidity());
        entity.setIdCard(riderExamine.getIdCard());
        logger.info("开通骑手时保存的资料====={}", JSON.toJSON(entity));
        int update = examineService.update(entity);
        if (update > 0) {

            riderExamine.setStatus(2);//把状态变为审核成功
            int updateStatus = examineService.updateExamine(riderExamine);
            if (updateStatus > 0) {
                attributes.addFlashAttribute(Remind.success());
                return "redirect:/console/rider/examine/riderInfor/";
            }
        }
        attributes.addFlashAttribute(Remind.danger());
        return "redirect:/console/rider/examine/riderInfor/";
    }

    /*审核失败*/
    @RequestMapping(value = "/riderInfor/{id}/remark", method = RequestMethod.GET)
    public String remark(@PathVariable int id, Model model) {
        RiderExamine entity = examineService.findById(id);
        model.addAttribute("entity",entity);
        return "console/examine/riderInfor/remark";
    }

    /*保存审核失败信息*/
    @RequestMapping(value = "/riderInfor/{id}/remark", method = RequestMethod.POST)
    public String saveRemark(@PathVariable int id, RedirectAttributes attributes, RiderExamine examine) {

            //不通过审核
        examine.setId(id);
        examine.setStatus(3);
        int i = examineService.updateExamine(examine);
        if (i > 0) {
            attributes.addFlashAttribute(Remind.success());
            return "redirect:/console/rider/examine/riderInfor/";
        }
        attributes.addFlashAttribute(Remind.danger());
        return "redirect:/console/rider/examine/riderInfor/";
    }




}
package com.vic.ck.console.config.controller;

import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.config.service.ThirdInterfaceService;
import com.vic.ck.entity.ThirdInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/console/config/thirdInterface")
public class ThirdInterfaceController extends BaseConsoleController {
    @Resource
    private ThirdInterfaceService thirdInterfaceService;

    /**
     * 跳转到支付管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/pay")
    public String payInterfaceList(Model model){
        List<ThirdInterface> payInterfaces = thirdInterfaceService.list("pay");
        model.addAttribute("payInterfaces",payInterfaces);
        return "/console/config/thirdInterface/payInterfaceList";
    }

    /**
     * 跳转到支付管理编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatePay/{id}",method = RequestMethod.GET)
    public String toUpdatePayInterface(@PathVariable("id")Integer id, Model model){
        ThirdInterface thirdInterface = thirdInterfaceService.findById(id);
        model.addAttribute("thirdInterface",thirdInterface);
        return "/console/config/thirdInterface/updatePayInterface";
    }

    /**
     * 修改支付管理信息
     * @param id
     * @param thirdInterface
     * @param attr
     * @return
     */
    @RequestMapping(value = "/updatePay/{id}", method = RequestMethod.POST)
    public String savePayInterface(@PathVariable("id")Integer id, ThirdInterface thirdInterface, RedirectAttributes attr){
        thirdInterface.setId(id);
        thirdInterfaceService.update(thirdInterface);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/thirdInterface/pay";
    }

    /**
     * 跳转到实名认证管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/realname")
    public String realnameInterfaceList(Model model){
        List<ThirdInterface> realnameInterfaces = thirdInterfaceService.list("realname");
        model.addAttribute("realnameInterfaces",realnameInterfaces);
        return "/console/config/thirdInterface/realnameInterfaceList";
    }

    /**
     * 跳转到实名认证管理编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateRealname/{id}",method = RequestMethod.GET)
    public String toUpdateRealnameInterface(@PathVariable("id")Integer id, Model model){
        ThirdInterface thirdInterface = thirdInterfaceService.findById(id);
        model.addAttribute("thirdInterface",thirdInterface);
        return "/console/config/thirdInterface/updateRealnameInterface";
    }

    /**
     * 修改实名认证管理信息
     * @param id
     * @param thirdInterface
     * @param attr
     * @return
     */
    @RequestMapping(value = "/updateRealname/{id}", method = RequestMethod.POST)
    public String saveRealnameInterface(@PathVariable("id")Integer id, ThirdInterface thirdInterface, RedirectAttributes attr){
        thirdInterface.setId(id);
        thirdInterfaceService.update(thirdInterface);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/thirdInterface/realname";
    }

    /**
     * 跳转到地图管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/map")
    public String mapInterfaceList(Model model){
        List<ThirdInterface> mapInterfaces = thirdInterfaceService.list("map");
        model.addAttribute("mapInterfaces",mapInterfaces);
        return "/console/config/thirdInterface/mapInterfaceList";
    }

    /**
     * 跳转到地图管理编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateMap/{id}",method = RequestMethod.GET)
    public String toUpdateMapInterface(@PathVariable("id")Integer id, Model model){
        ThirdInterface thirdInterface = thirdInterfaceService.findById(id);
        model.addAttribute("thirdInterface",thirdInterface);
        return "/console/config/thirdInterface/updateMapInterface";
    }

    /**
     * 修改地图管理信息
     * @param id
     * @param thirdInterface
     * @param attr
     * @return
     */
    @RequestMapping(value = "/updateMap/{id}", method = RequestMethod.POST)
    public String saveMapInterface(@PathVariable("id")Integer id, ThirdInterface thirdInterface, RedirectAttributes attr){
        thirdInterface.setId(id);
        thirdInterfaceService.update(thirdInterface);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/thirdInterface/map";
    }

    /**
     * 跳转到消息推送页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/message")
    public String messageInterfaceList(Model model){
        List<ThirdInterface> messageInterfaces = thirdInterfaceService.list("message");
        model.addAttribute("messageInterfaces",messageInterfaces);
        return "/console/config/thirdInterface/messageInterfaceList";
    }

    /**
     * 跳转到消息推送编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateMessage/{id}",method = RequestMethod.GET)
    public String toUpdateMessageInterface(@PathVariable("id")Integer id, Model model){
        ThirdInterface thirdInterface = thirdInterfaceService.findById(id);
        model.addAttribute("thirdInterface",thirdInterface);
        return "/console/config/thirdInterface/updateMessageInterface";
    }

    /**
     * 修改地图管理信息
     * @param id
     * @param thirdInterface
     * @param attr
     * @return
     */
    @RequestMapping(value = "/updateMessage/{id}", method = RequestMethod.POST)
    public String saveMessageInterface(@PathVariable("id")Integer id, ThirdInterface thirdInterface, RedirectAttributes attr){
        thirdInterface.setId(id);
        thirdInterfaceService.update(thirdInterface);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/thirdInterface/message";
    }

    /**
     * 跳转到短信管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/SMS")
    public String SMSInterfaceList(Model model){
        List<ThirdInterface> SMSInterfaces = thirdInterfaceService.list("SMS");
        model.addAttribute("SMSInterfaces",SMSInterfaces);
        return "/console/config/thirdInterface/SMSInterfaceList";
    }

    /**
     * 跳转到短信管理编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateSMS/{id}",method = RequestMethod.GET)
    public String toUpdateSMSInterface(@PathVariable("id")Integer id, Model model){
        ThirdInterface thirdInterface = thirdInterfaceService.findById(id);
        model.addAttribute("thirdInterface",thirdInterface);
        return "/console/config/thirdInterface/updateSMSInterface";
    }

    /**
     * 修改短信管理信息
     * @param id
     * @param thirdInterface
     * @param attr
     * @return
     */
    @RequestMapping(value = "/updateSMS/{id}", method = RequestMethod.POST)
    public String saveSMSInterface(@PathVariable("id")Integer id, ThirdInterface thirdInterface, RedirectAttributes attr){
        thirdInterface.setId(id);
        thirdInterfaceService.update(thirdInterface);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/thirdInterface/SMS";
    }

    /**
     * 跳转到OAuth登录页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/OAuth")
    public String OAuthInterfaceList(Model model){
        List<ThirdInterface> OAuthInterfaces = thirdInterfaceService.list("OAuth");
        model.addAttribute("OAuthInterfaces",OAuthInterfaces);
        return "/console/config/thirdInterface/oAuthInterfaceList";
    }

    /**
     * 跳转到OAuth登录编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateOAuth/{id}",method = RequestMethod.GET)
    public String toUpdateOAuthInterface(@PathVariable("id")Integer id, Model model){
        ThirdInterface thirdInterface = thirdInterfaceService.findById(id);
        model.addAttribute("thirdInterface",thirdInterface);
        return "/console/config/thirdInterface/updateOAuthInterface";
    }

    /**
     * 修改OAuth登录信息
     * @param id
     * @param thirdInterface
     * @param attr
     * @return
     */
    @RequestMapping(value = "/updateOAuth/{id}", method = RequestMethod.POST)
    public String saveOAuthInterface(@PathVariable("id")Integer id, ThirdInterface thirdInterface, RedirectAttributes attr){
        thirdInterface.setId(id);
        thirdInterfaceService.update(thirdInterface);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/thirdInterface/OAuth";
    }

    /**
     * 跳转到分享管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/share")
    public String shareInterfaceList(Model model){
        List<ThirdInterface> shareInterfaces = thirdInterfaceService.list("share");
        model.addAttribute("shareInterfaces",shareInterfaces);
        return "/console/config/thirdInterface/shareInterfaceList";
    }

    /**
     * 跳转到分享管理编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateShare/{id}",method = RequestMethod.GET)
    public String toUpdateShareInterface(@PathVariable("id")Integer id, Model model){
        ThirdInterface thirdInterface = thirdInterfaceService.findById(id);
        model.addAttribute("thirdInterface",thirdInterface);
        return "/console/config/thirdInterface/updateShareInterface";
    }

    /**
     * 修改分享管理信息
     * @param id
     * @param thirdInterface
     * @param attr
     * @return
     */
    @RequestMapping(value = "/updateShare/{id}", method = RequestMethod.POST)
    public String saveShareInterface(@PathVariable("id")Integer id, ThirdInterface thirdInterface, RedirectAttributes attr){
        thirdInterface.setId(id);
        thirdInterfaceService.update(thirdInterface);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/thirdInterface/share";
    }

    /**
     * 跳转到敏感词管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/sensitive")
    public String sensitiveInterfaceList(Model model){
        List<ThirdInterface> sensitiveInterfaces = thirdInterfaceService.list("sensitive");
        model.addAttribute("sensitiveInterfaces",sensitiveInterfaces);
        return "/console/config/thirdInterface/sensitiveInterfaceList";
    }

    /**
     * 跳转到敏感词管理编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateSensitive/{id}",method = RequestMethod.GET)
    public String toUpdateSensitiveInterface(@PathVariable("id")Integer id, Model model){
        ThirdInterface thirdInterface = thirdInterfaceService.findById(id);
        model.addAttribute("thirdInterface",thirdInterface);
        return "/console/config/thirdInterface/updateSensitiveInterface";
    }

    /**
     * 修改敏感词管理信息
     * @param id
     * @param thirdInterface
     * @param attr
     * @return
     */
    @RequestMapping(value = "/updateSensitive/{id}", method = RequestMethod.POST)
    public String saveSensitiveInterface(@PathVariable("id")Integer id, ThirdInterface thirdInterface, RedirectAttributes attr){
        thirdInterface.setId(id);
        thirdInterfaceService.update(thirdInterface);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/thirdInterface/sensitive";
    }
}

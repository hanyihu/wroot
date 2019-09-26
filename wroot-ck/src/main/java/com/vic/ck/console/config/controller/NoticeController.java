package com.vic.ck.console.config.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.config.lookup.NoticeLookup;
import com.vic.ck.console.config.service.NoticeService;
import com.vic.ck.entity.Notice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 公告管理控制器
 * @author houhaoran
 */
@Controller
@RequestMapping(value = "/console/config/notice")
public class NoticeController extends BaseConsoleController {
    private static Log log = LogFactory.getLog(NoticeController.class);
    @Resource
    private NoticeService noticeService;

    /**
     * 跳转到公告管理页面
     * @param model
     * @return 公告管理页面
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String page(Model model){
        PageInfo<Notice> pager = noticeService.list(getLookup());
        model.addAttribute("pager",pager);
        return "console/config/notice/list";
    }

    /**
     * 重写查询条件
     * @return
     */
    protected Lookup instanceLookup(){
        return new NoticeLookup();
    }

    /**
     * 查询
     * @param lookup 查询条件
     * @return 重定向到公告管理列表页
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(NoticeLookup lookup){
        setLookup(lookup);
        return "redirect:/console/config/notice/";
    }

    /**
     * 跳转到新增公告管理页面
     * @param model
     * @return 公告管理新增页面
     */
    @RequestMapping(value = "/update/0", method = RequestMethod.GET)
    public String add(Model model){
        return "console/config/notice/getUpdateNotice";
    }

    /**
     * 跳转到修改公告管理页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model){
        try{
            Notice notice = noticeService.getNoticeById(id);
            model.addAttribute("notice",notice);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "console/config/notice/getUpdateNotice";
    }

    /**
     * 保存新增/修改的公告管理
     * @return 重定向到公告管理列表页面
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String save(@PathVariable Integer id, Notice notice){
        try{
            notice.setId(id);
            notice.setIssue_user_id(getPrincipal().getUserId());
            notice.setIssue_user_name(getPrincipal().getUsername());
            notice.setIssue_city_id(getPrincipal().getCityId());
            notice.setIssue_time(new Date());
            if (id == 0) {
                noticeService.insert(notice);
            }else if (id > 0){
                noticeService.update(notice);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "redirect:/console/config/notice/";
    }

    /**
     * 单个删除
     * @param id
     * @param attr
     * @return
     */
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id")Integer id, RedirectAttributes attr){
        noticeService.deleteNotice(id);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/notice/";
    }

    /**
     * 批量删除
     * @param ids
     * @param attr
     * @return
     */
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public String batchDelete(int[] ids,RedirectAttributes attr){
        noticeService.deleteNotice(ids);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/notice/";
    }

}

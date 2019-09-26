package com.vic.ck.console.config.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.config.lookup.PromptLookup;
import com.vic.ck.console.config.service.PromptService;
import com.vic.ck.entity.BasicConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * 提示音管理控制器
 * @author houhaoran
 */
@Controller
@RequestMapping(value = "/console/config/prompt")
public class PromptController extends BaseConsoleController {
    @Resource
    private PromptService promptService;

    /**
     * 跳转到提示音管理列表页
     * @param model
     * @return 提示音管理列表页
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        PageInfo<BasicConfig> pager = promptService.list(getLookup());
        model.addAttribute("pager",pager);
        return "console/config/prompt/list";
    }

    /**
     * 重写提示音管理查询条件
     * @return
     */
    protected Lookup instanceLookup(){
        return new PromptLookup();
    }

    /**
     * 查询
     * @param lookup 查询条件
     * @return 重定向到提示音管理列表页
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(PromptLookup lookup){
        setLookup(lookup);
        return "redirect:/console/config/prompt/";
    }

    /**
     * 跳转到新增提示音页面
     * @param model
     * @return 提示音管理新增页面
     */
    @RequestMapping(value = "/update/0", method = RequestMethod.GET)
    public String add(Model model){
        return "console/config/prompt/getUpdatePrompt";
    }

    /**
     * 跳转到修改提示音页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id")Integer id, Model model){
        BasicConfig config = promptService.getConfigById(id);
        model.addAttribute("config",config);
        return "console/config/prompt/getUpdatePrompt";
    }

    /**
     * 保存
     * @param id
     * @param config
     * @param attr
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String save(@PathVariable("id")Integer id, BasicConfig config, RedirectAttributes attr){
        config.setId(id);
        if(id == 0){
            promptService.insert(config);
            attr.addFlashAttribute(Remind.success());
        }else if(id > 0){
            promptService.update(config);
            attr.addFlashAttribute(Remind.success());
        }else {
            attr.addFlashAttribute(Remind.danger());
        }
        return "redirect:/console/config/prompt/";
    }

    /**
     * 单个删除
     * @param id
     * @param attr
     * @return
     */
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id, RedirectAttributes attr){
        promptService.delete(id);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/prompt/";
    }

    /**
     * 批量删除
     * @param ids
     * @param attr
     * @return
     */
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public String delete(int[] ids, RedirectAttributes attr){
        promptService.delete(ids);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/prompt/";
    }
}

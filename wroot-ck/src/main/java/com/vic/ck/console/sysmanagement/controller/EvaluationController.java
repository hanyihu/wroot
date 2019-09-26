package com.vic.ck.console.sysmanagement.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.sysmanagement.lookup.EvaluationLookup;
import com.vic.ck.console.sysmanagement.service.EvaluationService;
import com.vic.ck.entity.CommodityEvaluation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/console/sysmanagement/evaluation")
public class EvaluationController extends BaseConsoleController {
    @Resource
    private EvaluationService evaluationService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        PageInfo<CommodityEvaluation> pager = evaluationService.list(getLookup());
        model.addAttribute("pager",pager);
        return "/console/sysmanagement/evaluation/list";
    }

    /**
     * 重写查询条件
     * @return
     */
    protected Lookup instanceLookup(){
        return new EvaluationLookup();
    }

    /**
     * 查询
     * @param lookup 查询条件
     * @return 重定向到公告管理列表页
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(EvaluationLookup lookup){
        setLookup(lookup);
        return "redirect:/console/sysmanagement/evaluation/";
    }

    /**
     * 跳转到编辑评价管理页面
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id")Integer id, Model model){
        CommodityEvaluation evaluation = evaluationService.findById(id);
        model.addAttribute("evaluation",evaluation);
        return "/console/sysmanagement/evaluation/updateEvaluation";
    }
    /**
     * 修改商品评价管理信息
     * @param id
     * @param evaluation
     * @param attr
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id")Integer id, CommodityEvaluation evaluation, RedirectAttributes attr){
        evaluation.setId(id);
        if(evaluation.getStatus() == null || "".equals(evaluation.getStatus())){
            evaluation.setStatus(1);
        }
        evaluationService.update(evaluation);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/sysmanagement/evaluation/";
    }
}

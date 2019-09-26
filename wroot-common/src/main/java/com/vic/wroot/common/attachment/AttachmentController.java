/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vic.wroot.common.attachment;

import com.vic.wroot.common.attachment.service.AttachmentService;
import com.vic.wroot.common.po.AjaxResponse;
import com.vic.wroot.common.po.UeditorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 文件上传.
 * 应该每个子项目都有的功能，故剥离至Common
 * 上传到本地后台，然后最终上传到文件服务器
 */
@Controller
public class AttachmentController {
    private Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Resource
    private AttachmentService attachmentService;


    @RequestMapping(value = "/ajax/upfile", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public AjaxResponse upfile(@RequestParam("upfile") MultipartFile file, @RequestParam(required = false, defaultValue = "unknow") String module) {
        AjaxResponse response = new AjaxResponse();
        if (file.isEmpty()) {
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setMessage("没有上传任何文件");
            return response;
        }
        try {
            response = attachmentService.upload(file.getOriginalFilename(), file.getInputStream(), module);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }

    /**
     * 百度富文本上传文件
     *
     * @throws IOException
     */
    @RequestMapping("/ajax/ujload")
    @ResponseBody
    public UeditorResponse config(@RequestParam("upfile") MultipartFile file) {

        AjaxResponse response = new AjaxResponse();
        try {

            response = attachmentService.upload(file.getOriginalFilename(), file.getInputStream());
            logger.info("百度富文本上传文件 返回response({}", response);
        } catch (Exception e) {
            logger.warn(ExceptionUtils.getStackTrace(e));
        }

        UeditorResponse ueditorResponse = UeditorResponse.toUeditor(response);
        return ueditorResponse;

    }

}

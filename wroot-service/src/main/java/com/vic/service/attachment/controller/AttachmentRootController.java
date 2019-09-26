package com.vic.service.attachment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vic.service.attachment.service.AttachmentRootService;
import com.vic.service.base.BaseController;
import com.vic.wroot.common.po.AjaxResponse;
import com.vic.wroot.common.po.Attachment;
import com.vic.wroot.common.po.BaseResponse;
import com.vic.wroot.common.util.CnToSpell;
import com.vic.wroot.common.util.CommonUtils;

/**
 * 附件相关
 * 
 * @author VIC
 *
 */
@Controller
public class AttachmentRootController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private AttachmentRootService attachmentRootService;
	
//	private static final String COMMON_BROKEN_IMAGE_REDIREDTION = "/files/avatar/0.png";
	private static final String COMMON_BROKEN_IMAGE_REDIREDTION = "/attachment/files/avatar/0.png";
	/**
	 * 上传文件 真正的上传
	 * @return
	 */
	@RequestMapping(value = "/attachment/ajax/upfile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse upfile(@RequestParam("upfile") MultipartFile file,@RequestParam(required=false, defaultValue="unknow") String module) {
		logger.info("上传文件");
		AjaxResponse response = new AjaxResponse();
		if (file.isEmpty()) {
			response.setCode(HttpStatus.NO_CONTENT.value());// 204
			response.setMessage("没有上传任何文件");
			return response;
		}
		String originalFilename = file.getOriginalFilename();
		originalFilename = originalFilename.replaceAll("[///?:@=&]", "");// 去掉一些特殊的字符
		originalFilename = CnToSpell.getFullSpell(originalFilename);// 汉语转拼音

		InputStream in = null;
		try {
			in = file.getInputStream();
			Attachment attachment = attachmentRootService.createAttachment(originalFilename);
			attachment.setContentType(CommonUtils.guessContentTypeFromName(originalFilename));
			attachment.setModule(module);
			File realFile = attachment.getRealFile();
			FileUtils.copyInputStreamToFile(in, realFile);
			attachmentRootService.insertTemporaryAttachment(attachment);// 插入数据库 临时态
			response.setSize(realFile.length());
			response.setValue(attachment.getPath());
			response.setId(attachment.getId());
			response.setUrl(attachmentRootService.getAttachmentUrl(attachment.getId()));
			response.setFileName(originalFilename);
			return response;

		} catch (IOException e) {
			e.printStackTrace();
			response.setCode(HttpStatus.SERVICE_UNAVAILABLE.value());
			response.setMessage(e.getMessage());
			return response;
		} finally {
			logger.info("上传文件返回结果 {}", CommonUtils.toJson(response) );
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.warn("关闭上传文件的流的错误, ", e);
			}
		}

	}

	/**
	 * 流下载文件
	 * @return
	 */
	@RequestMapping(value = "/attachment/ajax/loadfile/{id}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable int id) {
		Attachment attachment = attachmentRootService.findAttachment(id);
		try {
			File file = attachment.getAbsoluteFile();
			if (file == null) {
				throw new IOException();
			}
			response.setContentType(attachment.getContentType());
			response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 访问一个附件
	 * @return
	 */
	@RequestMapping(value = "/attachment/ajax/visit/{id}", method = RequestMethod.GET)
	public String visit(@PathVariable int id){
		Attachment attachment = attachmentRootService.findAttachment(id);
		
		if(attachment != null && !StringUtils.isBlank(attachment.getPath())) {
			return "redirect:" + attachmentRootService.getHost() + attachment.getPath();
		}
		return "redirect:" + attachmentRootService.getBrokenAttachment();
		
	}
	
	
	/**
	 * 访问一个空附件
	 * @return
	 */
	@RequestMapping(value = "/attachment/ajax/visit/null", method = RequestMethod.GET)
	public String viusit(){
		return "redirect:" + COMMON_BROKEN_IMAGE_REDIREDTION;
	}
	

	/**
	 * 修改一个文件的状态
	 * @return
	 */
	@RequestMapping(value = "/attachment/ajax/update", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse update(int id, @RequestParam(defaultValue="false")boolean temporary){
		try{
			attachmentRootService.updateTemporary(temporary, id);
		}catch(Exception e) {
			return BaseResponse.error().setMsg( e + "");
		}
		return BaseResponse.success();
	}
	
	/**
	 * 修改多个文件的状态
	 * @param ids 形如1-2-3-4
	 * @param temporary true-临时 false-非临时的
	 * @return
	 */
	@RequestMapping(value = "/attachment/ajax/batchupdate", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse batchUpdate(String ids, @RequestParam(defaultValue="false")boolean temporary){
		if(!ids.matches("(\\d+|\\d+-|\\d+-\\d+)+")){
			return BaseResponse.error().setMsg("不合法的字符");
		}
		try{
			attachmentRootService.updateTemporary(temporary, toIntArr(ids.split("-")));
		}catch(Exception e) {
			return BaseResponse.error().setMsg( e + "");
		}
		return BaseResponse.success();
	}
	
	
	private int[] toIntArr(String[] strs){
		int len = strs.length;
		int[] arr = new int[len]; 
		for(int i =0; i<len; i++) {
			arr[i] = Integer.parseInt(strs[i]);
		}
		return arr;
	}
	
	
}

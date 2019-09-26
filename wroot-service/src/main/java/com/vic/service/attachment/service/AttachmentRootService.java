package com.vic.service.attachment.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vic.service.attachment.mapper.AttachmentMapper;
import com.vic.wroot.common.po.Attachment;
import com.vic.wroot.common.tool.Tools;

@Service
public class AttachmentRootService {

	/**存放的附件相对于项目的位置*/
	private static final String UPLOAD_PATH = File.separator + "files" + File.separator + "upload";
	
	/**文件下载时候的相对根目录*/
	private static final String DOWNLOAD_PATH = "/files/upload";
	
	
	protected static final String ATTACHMENT_REG = "/attachment/ajax/visit/(\\d+)\"";
	
	@Resource
	private AttachmentMapper attachmentMapper;
	
	@Autowired(required = false)
	private ServletContext context;
	
	@Value("#{conf['attachment.host']}")
    private String host;

	
	/**
	 * 构建附件实体
	 * 2016年10月31日 by VIC
	 * 为了静态文件可直接访问，把附件放在项目中，后期附件项目应该单独出来作为一个独立的项目运行
	 *  绝对路径：  配置的地址 + 年 +月 + 日 +文件英文名-UUID 
	 *  访问路径  年 +月 + 日 +文件英文名-UUID
	 * @param module 
	 * @return
	 * @throws IOException 
	 */
	public Attachment createAttachment(String originalFilename) throws IOException {
		String uuid = UUID.randomUUID().toString();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		String root = getRelativeRoot();
		
		StringBuilder fileSb = new StringBuilder().append(root);
		fileSb.append(File.separator).append(year);
		fileSb.append(File.separator).append(month);
		fileSb.append(File.separator).append(day);
		fileSb.append(File.separator).append(originalFilename).append("-").append(uuid);
		
		StringBuilder pathSb = new StringBuilder().append(DOWNLOAD_PATH);
		pathSb.append("/").append(year);
		pathSb.append("/").append(month);
		pathSb.append("/").append(day);
		pathSb.append("/").append(originalFilename).append("-").append(uuid);
		return Attachment.create(fileSb.toString(), pathSb.toString(), originalFilename);
	}
	
	/**获得项目相对的根路径 + 配置的路径*/
	private String getRelativeRoot(){
		return context.getRealPath("/") + UPLOAD_PATH ;
	}
	public static void main(String[] args) {
	}

	/**插入临时文件到数据库*/
	public void insertTemporaryAttachment(Attachment attachment) {
		attachmentMapper.insertTemporaryAttachment(attachment);
	}

	public Attachment findAttachment(int id) {
		return attachmentMapper.findAttachment(id);
	}

	/**
	 * 设想附件应该是一个独立的服务系统 这里获取附件服务器的host
	 * @return
	 */
	public String getHost(){
		if(StringUtils.isBlank(host)){
			HttpServletRequest request = Tools.currentRequest();
			return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
		}
		return host;
	}
	
	/**获得一个空附件的地址*/
	public String getBrokenAttachment(){
		return getHost() + "/attachment/ajax/visit/null";
	}
	
	/**附件的访问地址*/
	public String getAttachmentUrl(int id){
		return getHost() + "/attachment/ajax/visit/" + id;
	} 

	/**修改附件状态*/
	public void updateTemporary(boolean temporary, int... ids) {
		attachmentMapper.updateTemporary(temporary, ids);
	}
	

}

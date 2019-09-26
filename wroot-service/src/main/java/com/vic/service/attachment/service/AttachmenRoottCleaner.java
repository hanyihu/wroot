package com.vic.service.attachment.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vic.service.attachment.mapper.AttachmentMapper;
import com.vic.wroot.common.po.Attachment;

/**
 * 清除附件定时任务
 * @author VIC
 *
 */
@Component
public class AttachmenRoottCleaner {

	@Resource
	private AttachmentMapper attachmentMapper;
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 凌晨四点 看海棠花未眠，定时清理附件
	 * @return
	 */
	@Scheduled( cron = "0 0 4 * * ?")
	public void clean(){
		logger.info("未眠之际，启动定时清除附件任务...");
		long t1 = System.currentTimeMillis();
		try{
			List<Attachment> list = attachmentMapper.getTemporaryAttachments();
			for(Attachment attachment : list) {
				File file = attachment.getRealFile();
				if(file !=null && file.exists()) {
					if(!file.isDirectory()){
						file = file.getParentFile();
					}
					logger.info("准备删除目录:{}", file.getAbsolutePath());
					FileUtils.deleteQuietly(file);
					logger.info("目录已删除");
					deleteEmpgyDirectory(file.getParentFile());
				}
				attachmentMapper.deleteAttachment(attachment.getId());
			}
		}catch(Exception e) {
			logger.warn("定时清除附件任务失败了....");
		}finally{
			long t2 = System.currentTimeMillis();
			logger.info("本次清除附件任务耗时 {}ms", t2-t1);
		}
	}

	/**
	 * 删除空的附件文件夹
	 * @return
	 */
	private void deleteEmpgyDirectory(File file) {
		if(file.list().length == 0) {
			file.delete();
			deleteEmpgyDirectory(file.getParentFile());
		}
		
		
	}
}

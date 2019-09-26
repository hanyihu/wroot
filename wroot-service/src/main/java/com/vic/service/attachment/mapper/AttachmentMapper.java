package com.vic.service.attachment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.wroot.common.annotation.MybatisMapper;
import com.vic.wroot.common.po.Attachment;

@MybatisMapper
public interface AttachmentMapper {

	void insertTemporaryAttachment(Attachment attachment);
	
	Attachment findAttachment(@Param("id") int id);

	void updateTemporary(@Param("temporary")boolean temporary, @Param("ids")int[] ids);

	List<Attachment> getTemporaryAttachments();

	void deleteAttachment(@Param("id")int id);

}

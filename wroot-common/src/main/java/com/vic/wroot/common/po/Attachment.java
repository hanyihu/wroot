package com.vic.wroot.common.po;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 对应附件表
 * @author VIC
 *
 */
public class Attachment {
	
	private  int id;
	
	private String file;//绝对路径
	
	private File realFile;
	
	private String path;//相对路径
	
	private String creteTimeStr;//创建时间
	
	private Date createTime;
	
	private boolean temporary;
	
	private String module;//所属项目
	
	private String contentType;

	
	public Attachment() {
	}
	
	public static Attachment create(String file, String path, String fileName) throws IOException {
		File dir = new File(file);
		if(!dir.exists()){
			dir.mkdirs();
		}
		File realFile = new File(dir, fileName);
		realFile.createNewFile();
		return new Attachment(realFile, path, fileName);
	}

	public Attachment(File realFile, String path, String fileName){
		this.realFile = realFile;
		this.path = path + "/" + fileName;
		this.file = realFile.getAbsolutePath();
	}
	
	public File getAbsoluteFile(){
		if(StringUtils.isNotBlank(file)){
			return new File(file);
		}
		return null;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreteTimeStr() {
		return creteTimeStr;
	}

	public void setCreteTimeStr(String creteTimeStr) {
		this.creteTimeStr = creteTimeStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isTemporary() {
		return temporary;
	}

	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}


	public File getRealFile() {
		if(realFile == null && StringUtils.isNotBlank(file)){
			return new File(file);
		}
		return realFile;
	}

	public void setRealFile(File realFile) {
		this.realFile = realFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}


}

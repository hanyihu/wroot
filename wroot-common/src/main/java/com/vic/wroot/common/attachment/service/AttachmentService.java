package com.vic.wroot.common.attachment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.craw.BaseCrawl;
import com.vic.wroot.common.craw.CrawlConnect;
import com.vic.wroot.common.po.AjaxResponse;
import com.vic.wroot.common.po.AuthorityData;
import com.vic.wroot.common.po.BaseResponse;
import com.vic.wroot.common.tool.Tools;
import com.vic.wroot.common.util.CommonUtils;
import com.vic.wroot.common.util.PropertiesUtil;
import com.vic.wroot.common.util.RegexUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 附件相关service
 *     需要配置conf： resource.host 和 url.timeout
 * @author VIC
 *
 */
@Service
public class AttachmentService extends BaseCrawl {

	private static Logger logger = LoggerFactory.getLogger(AttachmentService.class);

	protected static final String ATTACHMENT_REG = "/attachment/ajax/visit/(\\d+)\"";

	@Value("#{conf['resource.host']}")
	private String host;

	@Value("#{conf['url.timeout']}")
	private int timeout;

	/**
	 * 上传文件
	 * 
	 * @param in
	 * @return
	 */
	public AjaxResponse upload(String fileName, InputStream in) {
		logger.info("上传文件{}", fileName);
		AjaxResponse r = new AjaxResponse();
		String url = host + "/attachment/ajax/upfile";
		try {
			CrawlConnect connect = con(url);
			//setHeader(connect);
			String text = connect.data("upfile", fileName, in).postBodyText();
			r = new ObjectMapper().readValue(text, AjaxResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return r;
	}
	
	/**
	 * 
	 */
	public AjaxResponse upload(String fileName, InputStream in, String module) {
		logger.info("上传文件{}", fileName);
		AjaxResponse r = new AjaxResponse();
		String url = host + "/attachment/ajax/upfile";
		try {
			CrawlConnect connect = con(url);
			//setHeader(connect);
			String text = connect.data("upfile", fileName, in).data("module", module).postBodyText();
			r = new ObjectMapper().readValue(text, AjaxResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return r;
	}
	//module

	/**
	 * 修改附件状态
	 * @param temporary 是否为临时态
	 * @param ids
	 * @return
	 */
	public boolean updateTemporary(boolean temporary, Integer... ids) {
		if(ids.length <=0) {
			return true;
		}
		ids = CommonUtils.deleteNullInArr(ids);
		if(ids.length == 0) return true;
		
		logger.info("修改附件{}的状态为{}", ids, temporary);
		/**StringUtils.join(array)  这里的array用int类型不行啊*/
		String url = host + "/attachment/ajax/batchupdate";
		BaseResponse r = new BaseResponse();
		try {
			CrawlConnect connect = con(url);
			setHeader(connect);
			String text = connect.data("ids", StringUtils.join(ids, "-")).
					data("temporary", String.valueOf(temporary)).postBodyText();
			r = new ObjectMapper().readValue(text, BaseResponse.class);
			if(r.getCode() == 0) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return false;
		
		
	}
	
	/**设置header信息由于验证是否可访问附件项目*/
	private void setHeader(CrawlConnect connect){
		connect.header("module", PropertiesUtil.getStringByKey("module.name")).header("password", DigestUtils.md5Hex(PropertiesUtil.getStringByKey("module.password")));
	}

	/** 修改附件状态 */
	public void updateTemporary(boolean temporary, String content) {
		Integer[] ids = getAttachmentIds(content);
		if (ids != null) {
			updateTemporary(temporary, ids);
		}
	}

	/** 获得文本内容中的附件id数组 */
	public static Integer[] getAttachmentIds(String content) {
		List<String> list = RegexUtil.getList(content, ATTACHMENT_REG, 1);
		if (list.isEmpty()) {
			return new Integer[]{};
		}
		List<Integer> intList = CommonUtils.toIntList(list.toArray(new String[0]));
		int size = intList.size();
		Integer[] arr = new Integer[size];
		for (int i = 0; i < size; i++) {
			arr[i] = intList.get(i);
		}
		return arr;
	}
	
	/**
	 *  从对象中获取附件的id集合
	 *  @see AttachmentFlag
	 */
	public static <T> List<Integer> getAttachmentIds(T t) {
		List<Integer> list = new ArrayList<Integer>();
		if(t == null) {
			return list;
		}
		try {
			Field[] fileds = t.getClass().getDeclaredFields();//暂时不考虑父类的
			for(Field f : fileds) {
				if(f.isAnnotationPresent(AttachmentFlag.class)) {
					AttachmentFlag flag = f.getAnnotation(AttachmentFlag.class);
					PropertyDescriptor pd = new PropertyDescriptor(f.getName(), t.getClass());
					Method method = pd.getReadMethod();//获得get方法
					Object value = method.invoke(t);
					if(value == null || value.toString().equals("")) continue;
					switch (flag.value()) {
					case SIGN:
						if((value.toString()).matches("\\d+")){
							list.add(Integer.parseInt(value.toString()));
						}
						break;
					case SIGNS:
						list.addAll(CommonUtils.toIntList(value.toString().split(",")));
						break;
					case CONTENT:	
						Integer[] arr = getAttachmentIds(value.toString());
						if(arr != null && arr.length > 0) {
							list.addAll(new ArrayList<Integer>(Arrays.asList(arr)));
						}
						break;
					default:
						logger.info("反射{}字段{}获得的值为{}", new Object[] {t.getClass(), f.getName(), value});
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return list;
	}
	
	/**
	 * 分开对象中要删除和要新增的附件
	 *  需要 AttachmentFlag 注解
	 * @param old
	 * @param now
	 * @return
	 */
	public <T> void HandleOldAndNowAttachment(T old, T now) {
		AuthorityData data = AuthorityData.instance(getAttachmentIds(old), getAttachmentIds(now));
		updateTemporary(true, data.getNeedDeleteArr());
		updateTemporary(false, data.getNeedAddArr());
	 
	}
	
	/***
	 * 删除对象中的全部附件
	 */
	public <T> void deleteAttachmengFromObj(List<T> ts) {
		if(ts == null) return;
		List<Integer> list = new ArrayList<Integer>();
		for(T t : ts) {
			list.addAll(getAttachmentIds(t));
		}
		updateTemporary(true, list.toArray(new Integer[0]));
	}
	//删除对象中的全部附件
	public <T> void deleteAttachmengFromObj(T t) {
		updateTemporary(true,  getAttachmentIds(t).toArray(new Integer[0]));
	}
	
	/**
	 * 新增对象中的全部附件
	 */
	public <T> void addAttachmengFromObj(List<T> ts) {
		if(ts == null) return;
		List<Integer> list = new ArrayList<Integer>();
		for(T t : ts) {
			list.addAll(getAttachmentIds(t));
		}
		updateTemporary(false, list.toArray(new Integer[0]));
	}
	
	/**新增对象中的全部附件*/
	public <T> void addAttachmengFromObj(T t) {
		updateTemporary(false,  getAttachmentIds(t).toArray(new Integer[0]));
	}

	/**
	 * 设想附件应该是一个独立的服务系统 这里获取附件服务器的host
	 * 
	 * @return
	 */
	public String getStaticAttachmentHost() {
		if (StringUtils.isBlank(host)) {
			HttpServletRequest request = Tools.currentRequest();
			return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"
					+ request.getContextPath();
		}
		return host;
	}

	@Override
	protected int getTimeout() {
		return timeout;
	}
	
	public static void main(String[] args) throws IOException {
		
		/*Object a = "1";
		
		System.out.println(((String)a).matches("\\d+"));
		if("1".equals("1")) return;
		Integer b = Integer.parseInt((String)a);
		System.out.println(b);
		
		Test t = new Test(2, 3, "1,2,3", "xxxxxxxxxxxx/attachment/ajax/visit/6asdasdasd/attachment/ajax/visit/9");
		for(Integer i : getAttachmentIds(t)) {
			System.out.print (i + "\t");
		}
		// "/attachment/ajax/visit/(\\d+)\"";
		System.out.println();
		File file = new File("F:/test.txt");
		List<String> list =  FileUtils.readLines(file, "UTF-8");
		for(String str : list) {
			System.out.println(str);
			
			for(Integer i :getAttachmentIds(str)) {
				System.out.println(i);
			}
		}*/
		
		
	}

}

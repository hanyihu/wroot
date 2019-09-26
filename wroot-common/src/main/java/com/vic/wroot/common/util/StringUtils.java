package com.vic.wroot.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }
    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 判断字符串是否是空串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        boolean rpt = false;
        if (null == str || str.length() < 1 || "".equals(str) || "null".equalsIgnoreCase(str)) {
            rpt = true;
        }
        return rpt;
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }


    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }


    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     *
     * @param objectString 对象串
     *                     例如：row.user.id
     *                     返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }

    /**
     * @param obj       实体对象
     * @param fieldName 字段名
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getString(Object obj, String fieldName) {
        String result = null; // 返回对象
        Object filed = null;
        if (obj instanceof Map) {
            Map<String, Object> objMap = (Map<String, Object>) obj;
            filed = objMap.get(fieldName);
        } else {
            filed = Reflections.invokeGetter(obj, fieldName);
        }
        if (filed == null || filed == null)
            result = "";
        if (null == filed || filed.toString().equals("NaN") || filed.toString().equals("--") || filed.toString().equals("Infinity")) {
            result = "";
        } else if (filed instanceof BigDecimal) { //
            result = filed.toString();
        } else if (filed instanceof Double) {  //
            result = filed.toString();
        } else if (filed instanceof Float) {  //
            result = filed.toString();
        } else if (filed instanceof String) {
            result = filed.toString();
        } else if (filed instanceof Integer) {
            result = filed.toString();
        } else if (filed instanceof Long) {
            result = filed.toString();
        } else if (filed instanceof Date) {
            result = DateFormatUtils.format((Date) filed, Reflections.JsonFormatAnnotation(obj, fieldName));
        } else {
            result = (String) filed;
        }
        return result;
    }

    /**
     * c 要填充的字符
     * length 填充后字符串的总长度
     * content 要格式化的字符串
     * 格式化字符串，右对齐
     */
    public static String flushRight(char c, long length, String content) {
        String str = "";
        String cs = "";
        if (content.length() > length) {
            str = content;
        } else {
            for (int i = 0; i < length - content.length(); i++) {
                cs = cs + c;
            }
        }
        str = cs + content;
        return str;
    }

    /**
     * 对象 转成json字符串<br>
     * 出现异常则返回空字符串 异常记录到日志中
     *
     * @param obj 要转换的对象
     * @return 返回JSON字符串
     * @author Zev(张中伟)
     */
    public static String jsonEncode(Object obj) {
        ObjectMapper mapper2 = new ObjectMapper();
        StringWriter sw = new StringWriter();

        JsonGenerator gen;

        if (null == obj)
            return null;

        try {
            gen = new JsonFactory().createGenerator(sw);
            mapper2.writeValue(gen, obj);
            gen.close();
        } catch (IOException e) {

            logger.error("转换为JSON字符串时出错" + obj.toString());

        } finally {

        }

        String json = sw.toString();

        return json;
    }

    public static void main(String[] args) {
        System.out.println(flushRight('0', 4, "5"));
    }

}

package com.vic.wroot.common.util;

import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;




/**

 * 汉语转拼音工具类

 * @author VIC

 *

 */
public class CnToSpell {

    /**

     * 返回字符串的全拼,是汉字转化为全拼,其它字符不进行转换

     *

     * @param cnStr String 字符串

     * @return String 转换成全拼后的字符串

     */
    public static String getFullSpell(String cnStr) {
        if (StringUtils.isBlank(cnStr)) {
            return cnStr;
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        char[] input = cnStr.trim().toCharArray();
        StringBuilder output = new StringBuilder("");
        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output.append(temp[0]);
                } else if (Character.toString(input[i]).matches("[^\\x00-\\x80]") || input[i] == ' ') {
//                    output.append("");


                } else {
                    output.append(Character.toString(input[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
           
        }
        return output.toString();
    }
    /**

     * 获得汉语字符串的每个汉字的首字母小写  其它字符不进行转换

     * @param cnStr

     * @return

     */
    public static String getFirstSpell(String cnStr){
      if (StringUtils.isBlank(cnStr)) {
            return cnStr;
        }
    	HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
    	format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    	format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    	char[] input = cnStr.trim().toCharArray();
    	StringBuilder output = new StringBuilder("");
    	try{
    		for(int i = 0; i < input.length; i++) {
    			if(Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]")) {
    				String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
    				String full = temp[0];
    				if(StringUtils.isNotBlank(full)) {
    					output.append(full.charAt(0));
    				}
    			}else if(Character.toString(input[i]).matches("^\\x00-\\x80") || input[i] == ' ') {
    				
    			}else {
    				output.append(Character.toString(input[i]));
    			}
    		}
    	}catch(BadHanyuPinyinOutputFormatCombination e){
    		
    	}
    	return output.toString().toLowerCase();
    	
    }
    public static void main(String[] args) {
		String s = "测试要转的fA";
		System.out.println(getFullSpell(s));
		System.out.println("1234".charAt(1));
		System.out.println(getFirstSpell(s));
	}
}


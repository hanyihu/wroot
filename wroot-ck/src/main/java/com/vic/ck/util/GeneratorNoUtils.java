package com.vic.ck.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 生成一些码
 * @author VIC
 *
 */
public class GeneratorNoUtils {

    /**
     * 根据时间 ： 年月日时分秒毫秒 + 随机个位数 生成18位订单号
     *
     * @return
     */
    public static synchronized String generatorOrderNo() {
        Date date = new Date();
        String prefix = DateFormatUtils.format(date, "yyyyMMddHHmmssSSS");
        int suffix = new Random().nextInt(10); //最后一位暂时不确定
        return prefix + suffix;
    }
    
    /**
     * 生成swapno
     * @return
     */
    public static synchronized String  generatorSwapno(){
    	 Date date = new Date();
         String prefix = DateFormatUtils.format(date, "yyMMddHHmmss");
         int suffix = new Random().nextInt(99); //最后一位暂时不确定
         return "S" + prefix + suffix;
    }
    
    /**
     * 生成团购券码
     */
    public static synchronized String generatorTicketno() {
    	 Date date = new Date();
         String prefix = DateFormatUtils.format(date, "yyMMddHHmmss"); //12位
         int suffix = new Random().nextInt(99); 
         int last = new Random().nextInt(9); 
         return prefix + suffix + last;
    }
    /**
	 * 根据传入的两位字符串返回10位的券码
	 * @return
	 */
	public static String generateShortUuid(String prefix) {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return prefix + shortBuffer.toString();

	}
	private static String[] chars = new String[] {
			"a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" 
	};

}

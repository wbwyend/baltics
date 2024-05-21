package cn.baltics.springboot.starter.common.util;
/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/20 
 */
public final class StringUtil {
    /**
     * 判断String为null或长度为0
     * @param s String
     */
    public static boolean isNullOrBlank(String s) {
        return s == null || "".equals(s);
    }
}

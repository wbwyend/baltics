package cn.baltics.springboot.starter.common.util;

import java.util.Random;

/**
 *@func 验证码工具包
 *
 *@author wbwyend
 *@date 2024/05/20 
 */
public final class VerificationCodeUtil {
    /**
     * 生成验证码
     */
    public static String getNewCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}

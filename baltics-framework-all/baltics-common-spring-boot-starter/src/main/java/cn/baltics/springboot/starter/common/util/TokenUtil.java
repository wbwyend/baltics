package cn.baltics.springboot.starter.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultJwt;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import io.jsonwebtoken.impl.DefaultJwtParser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *@func token工具包
 *
 *@author wbwyend
 *@date 2024/05/20 
 */
public final class TokenUtil {

//    private static final long OVER_TIME = 7200000L;

    public static String createToken(String val, String sign) {
        JwtBuilder jwtBuilder = new DefaultJwtBuilder();
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("id", val);
        jwtBuilder.setClaims(chaim);
        jwtBuilder.signWith(SignatureAlgorithm.HS256, sign);
//        jwtBuilder.setExpiration();
        Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + 1000L));
        return jwtBuilder.compact();
    }

    public static String parseToken(String token, String sign) {
        return "";
    }
}

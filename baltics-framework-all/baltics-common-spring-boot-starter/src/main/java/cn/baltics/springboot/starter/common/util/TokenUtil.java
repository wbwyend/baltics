package cn.baltics.springboot.starter.common.util;

import cn.baltics.springboot.starter.convention.errorcode.BaseErrorCode;
import cn.baltics.springboot.starter.convention.exception.ClientException;
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

    private static final long OVER_TIME = 7200000L;
    private static final String TOKEN_ERROR = "Token验证错误";

    public static String createToken(String val, String sign) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setExpiration(new Date(System.currentTimeMillis() + OVER_TIME))
                .signWith(SignatureAlgorithm.HS256, sign)
                .claim("id", val)
                .compact();
    }

    public static String parseToken(String token, String sign) {
        String result = null;
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            result = (String) body.get("id");
        } catch (Exception e) {
            throw new ClientException(TOKEN_ERROR);
        }
        return result;
    }
}

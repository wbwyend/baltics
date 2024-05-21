package cn.baltics.springboot.starter.common.util;

import cn.baltics.springboot.starter.common.enums.TokenErrorCode;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Date;

/**
 *@func token工具包
 *
 *@author wbwyend
 *@date 2024/05/20 
 */
public final class TokenUtil {

    private static final long OVER_TIME = 7200000L;

    public static String createToken(String val, String sign) {
        return JWT.create()
                .withAudience(val)
                .withExpiresAt(new Date(System.currentTimeMillis() + OVER_TIME))
                .sign(Algorithm.HMAC256(sign));
    }

    public static boolean parseToken(String token, String sign) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();
        try {
            jwtVerifier.verify(token);
        } catch (TokenExpiredException e) {
            throw new ClientException(TokenErrorCode.TOKEN_EXPIRED_ERROR);
        } catch (Exception e) {
            throw new ClientException(TokenErrorCode.TOKEN_ERROR);
        }
        return true;
    }

    public static String getAudience(String token) {
        String result;
        try {
            result = JWT.decode(token).getAudience().get(0);
        } catch (Exception e) {
            throw new ClientException(TokenErrorCode.TOKEN_ERROR);
        }
        return result;
    }

}

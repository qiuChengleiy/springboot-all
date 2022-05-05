package com.example.shiro.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.shiro.dao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/5/5 10:57 AM
 * @description
 */
public class JwtUtil {

    private static Logger log = LoggerFactory.getLogger(JwtUtil.class);

    // 设置过期时间
    private static final long EXPIRE_TIME = 1000 * 72 * 36;

    // 设置秘钥 (这里推荐大家可以写入 yml配置文件里)
    private static final String Secret = "28ca017de15a57e206f0";

    /**
     * 校验 token是否正确
     *
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token, User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(Secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", user.getUserId())
                    .withClaim("roleId", user.getRole())
                    .build();
            verifier.verify(token);
            log.info("token is valid");
            return true;
        } catch (Exception e) {
            log.error("token is invalid{}", e.getMessage());
            return false;
        }
    }

    /**
     * 从 token中获取用户id
     *
     * @return token中包含的用户id
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 从 token中获取用户roleId
     *
     * @return token中包含的用户id
     */
    public static Integer getRoleId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("roleId").asInt();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成 token
     *
     * @param user
     * @return token
     */
    public static String sign(User user) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 这里可以加入秘钥
            Algorithm algorithm = Algorithm.HMAC256(Secret);

            // 这里可以存放于jwt中的内容信息，最后可以通过解密拿到
            return JWT.create()
                    .withClaim("userId", user.getUserId())
                    .withClaim("roleId", user.getRole())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("error：{}", e);
            return null;
        }
    }
}




package com.zj.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zj.sys.dto.TokenUser;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

/**
* @discription TODO
* @author 赵健
* @date 2022/10/16 10:55
* @version 1.0
*/
public class JwtTokenUtil {

    private final static String JWT_TOKEN_PREFIX = "138c7b90-52fd-41ee-9967-0877faf70a78";

    private final static long expireSecond = 60*30;

    /**
     * @description: 创建token字符串
     * @return: java.lang.String
     * @param: [user]
     * @author 赵健
     * @date 2022/10/16 10:56
     */

    public static String generateToken(TokenUser tokenUser){
        Instant nowInstant = Instant.now();
        Instant expireInstant = nowInstant.plusSeconds(expireSecond);
        ZonedDateTime dateTime = expireInstant.atZone(ZoneId.systemDefault());
        System.err.println(dateTime);
        String token = JWT.create()
                .withClaim("tokenUser", tokenUser.toMap())
                .withExpiresAt(expireInstant)
                .withIssuedAt(nowInstant)
                .sign(Algorithm.HMAC384(JWT_TOKEN_PREFIX));
        return token;
    }

    public static TokenUser getTokenUserFromToken(String token){
        JWTVerifier build = JWT.require(Algorithm.HMAC384(JWT_TOKEN_PREFIX)).build();
        DecodedJWT verify = build.verify(token);
        Claim claim = verify.getClaim("tokenUser");
        if(claim.isNull()){
            throw new JWTDecodeException("无效的token");
        }
        Map map = claim.as(Map.class);
        TokenUser tokenUser = TokenUser.mapToEntity(map);
        return tokenUser;
    }

    public static String refreshToken(String token){
        String finalToken;
        JWTVerifier build = null;
        try {
            build = JWT.require(Algorithm.HMAC384(JWT_TOKEN_PREFIX)).build();
        } catch (Exception e) {
            System.err.println("解析token错误，错误信息："+e.getMessage());
            return null;
        }
        DecodedJWT verify = build.verify(token);
        Claim claim = verify.getClaim("tokenUser");
        if(claim.isNull()){
            throw new JWTDecodeException("无效的token");
        }
        Map map = claim.as(Map.class);
        TokenUser tokenUser = TokenUser.mapToEntity(map);
        Instant expires = verify.getExpiresAtAsInstant();
        // 计算还有多长时间到期
        Duration duration = Duration.between(Instant.now(), expires);
        long seconds = duration.toMillis() / 1000;
        if(seconds<=(expireSecond/2)){
            finalToken = generateToken(tokenUser);
        }else{
            finalToken = null;
        }
        return finalToken;
    }



    public static void main(String[] args) {
        TokenUser tokenUser = new TokenUser();
        tokenUser.setUsername("dsfsdf");
        String s = generateToken(tokenUser);
        System.err.println("token="+s);
        TokenUser tokenUserFromToken = getTokenUserFromToken(s);
        System.err.println(tokenUserFromToken);


    }
}
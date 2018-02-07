package com.example.meet.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Alex
 * Date: 2018-02-06
 * Time: 14:24
 */
public class TokenUtils {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 签名秘钥
     */
    public static final String SECRET = "jinwangansha";

    /**
     * token过期时间，单位小时
     * */
    public static final Integer EXPIRE_HOUR = 360;

    /**
     * 生成token
     *
     * @param telephone 一般传入userName
     * @return
     */
    public static String createJwtToken(String telephone) {
        String issuer = "www.jinwangansha.com";
        String subject = "sheat120@sina.com";
        long ttlMillis = System.currentTimeMillis();
        return createJwtToken(telephone, issuer, subject, ttlMillis);
    }

    /**
     * 生成Token
     *
     * @param telephone        编号
     * @param issuer    该JWT的签发者
     * @param subject   该JWT所面向的用户
     * @param ttlMillis 签发时间
     * @return token String
     */
    public static String createJwtToken(String telephone, String issuer, String subject, long ttlMillis) {
        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        /* 过期时间 */
        Calendar calExp = Calendar.getInstance();
        calExp.add(Calendar.HOUR, EXPIRE_HOUR);

        JwtBuilder builder = Jwts.builder().setId(telephone)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

    // Sample method to validate and read the JWT
    public static Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static void main(String[] args) {
        System.out.println(TokenUtils.createJwtToken("18801064494"));
    }
}

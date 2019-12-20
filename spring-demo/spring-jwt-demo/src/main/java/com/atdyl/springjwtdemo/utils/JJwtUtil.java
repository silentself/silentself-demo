package com.atdyl.springjwtdemo.utils;

import io.jsonwebtoken.*;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Decoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dong YL
 * @version V1.0 2019/12/19 16:57
 */
public class JJwtUtil {

    private static final String privateKeyFilePath = "/private.pem";
    private static final String publicKeyFilePath = "/public.pem";

    private static int intervalHours = 24;

    /**
     * 日期类型小时加减计算
     *
     * @param date -
     * @param intervalHours -
     * @return -
     */
    public static Date addHours(Date date, int intervalHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, intervalHours);
        return calendar.getTime();
    }

    /**
     * 创建JWT
     *
     * @param commonParam 传递的公共参数
     * @return - token
     */
    public static String buildJwtRS256(Map<String, Object> commonParam) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
        Date date = addHours(new Date(), intervalHours);
        PrivateKey privateKey = null;
        try {
            // 读取私钥
            String key = readResourceKey(privateKeyFilePath);
            // 生成签名密钥
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        // jwt (Header+Payload+Signature)
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("snow-jwt-admin") //发行人
                .setSubject("token") //主题
                .setAudience("web-pc") //受众
                .setExpiration(date) //过期时间
                .signWith(signatureAlgorithm, privateKey);
        // jwt中需要传递的内容
        commonParam.forEach(builder::claim);
        return builder.compact();
    }

    private static String readResourceKey(String filePath) throws IOException {
        String key = IOUtils.toString(new FileInputStream(filePath), StandardCharsets.UTF_8);
        return key
                .replaceAll("\\-*BEGIN.*KEY\\-*", "")
                .replaceAll("\\-*END.*KEY\\-*", "");
    }

    /**
     * 验证token
     *
     * @param token -
     * @return - Payload 返回jwt的负载部分，包含当前用户信息
     */
    public static Claims parseJwtRS256(String token) {
        Claims claims = null;
        try {
            // 读取公钥
            String key = readResourceKey(publicKeyFilePath);
            // 生成签名公钥
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .requireAudience("web-pc")
                    .requireIssuer("snow-jwt-admin")
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return claims;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", 100000);

        String s = buildJwtRS256(map);
        System.err.println(s);

        Claims claims = parseJwtRS256(s);

        System.err.println(claims.toString());
    }
}

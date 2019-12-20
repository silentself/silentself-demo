package com.atdyl.springjwtdemo.utils;

import org.apache.commons.io.IOUtils;
import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

/**
 * @author Dong YL
 * @version V1.0 2019/12/16 11:43
 */
public class Jose4jJWTUtil {

    /**
     * 生成jwt,SHA256加密
     *
     * @return
     * @throws IOException
     */
    private static String createToken() throws IOException {
        PrivateKey privateKey = getPrivateKey(getPrivateKeyString());
        final JwtClaims claims = new JwtClaims();
        claims.setClaim("name", "jack");
        claims.setSubject("a@a.com");
        claims.setAudience("test");//用于验证签名是否合法，验证方必须包含这些内容才验证通过
        claims.setExpirationTimeMinutesInTheFuture(60 * 24 * 30);
        claims.setIssuedAtToNow();

        // Generate the payload
        final JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        jws.setPayload(claims.toJson());
        jws.setKeyIdHeaderValue(UUID.randomUUID().toString());

        // Sign using the private key
        jws.setKey(privateKey);
        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            return null;
        }
    }

    private static String getPrivateKeyString() throws IOException {
        //    生成方法：安装openssl,执行     openssl genrsa -out private.pem 2048
        return IOUtils.toString(new FileInputStream("/private.pem"), StandardCharsets.UTF_8);
    }

    private static String getPEMPublicKeyString() throws IOException {
        //    导出公钥方法：生成私钥(private.pem)后,执行    openssl rsa -in private.pem -outform PEM -pubout -out public.pem
        return IOUtils.toString(new FileInputStream("/public.pem"), StandardCharsets.UTF_8);
    }

    /**
     * 验证jwt
     *
     * @param token
     * @return
     * @throws Exception
     */
    private static JwtClaims verifyToken(String token) throws Exception {

        try {
            PublicKey publicKey = getPublicKey(getPEMPublicKeyString());

            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setVerificationKey(publicKey)
                    .setExpectedAudience("test")//用于验证签名是否合法，可以设置多个，且可设置必须存在项，如果jwt中不包含这些内容则不通过
                    .build();

            return jwtConsumer.processToClaims(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取PublicKey对象
     *
     * @param publicKeyBase64
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String publicKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String pem = publicKeyBase64
                .replaceAll("\\-*BEGIN.*KEY\\-*", "")
                .replaceAll("\\-*END.*KEY\\-*", "");
        Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(pem));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);
        System.out.println(publicKey);
        return publicKey;
    }

    /**
     * 获取PrivateKey对象
     *
     * @param privateKeyBase64
     * @return
     */
    private static PrivateKey getPrivateKey(String privateKeyBase64) {
        String privKeyPEM = privateKeyBase64
                .replaceAll("\\-*BEGIN.*KEY\\-*", "")
                .replaceAll("\\-*END.*KEY\\-*", "");

        // Base64 decode the data
        byte[] encoded = Base64.decodeBase64(privKeyPEM);

        try {
            DerInputStream derReader = new DerInputStream(encoded);
            DerValue[] seq = derReader.getSequence(0);

            if (seq.length < 9) {
                throw new GeneralSecurityException("Could not read private key");
            }

            // skip version seq[0];
            BigInteger modulus = seq[1].getBigInteger();
            BigInteger publicExp = seq[2].getBigInteger();
            BigInteger privateExp = seq[3].getBigInteger();
            BigInteger primeP = seq[4].getBigInteger();
            BigInteger primeQ = seq[5].getBigInteger();
            BigInteger expP = seq[6].getBigInteger();
            BigInteger expQ = seq[7].getBigInteger();
            BigInteger crtCoeff = seq[8].getBigInteger();

            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp,
                    primeP, primeQ, expP, expQ, crtCoeff);

            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(keySpec);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String token = createToken();

        System.err.println(token);

        token = token +"111";

        JwtClaims jwtClaims = verifyToken(token);

        System.err.println(jwtClaims.toJson());



    }


}

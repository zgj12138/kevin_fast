package io.kevin.modules.sys.oauth2;

import io.kevin.common.exception.GJException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Token 生成器
 * @author ZGJ
 * @date 2017/7/14 0:04
 **/
public class TokenGenerator {
    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String toHexString(byte[] data) {
        if(data == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(data.length * 2);
        for(byte b : data) {
            stringBuilder.append(hexCode[(b >> 4) & 0xF]);
            stringBuilder.append(hexCode[b & 0xF]);
        }
        return stringBuilder.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new GJException("生成Token失败");
        }
    }
    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }
}

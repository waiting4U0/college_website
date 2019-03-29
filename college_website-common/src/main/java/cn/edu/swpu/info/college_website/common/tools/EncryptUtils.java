package cn.edu.swpu.info.college_website.common.tools;

import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;


public class EncryptUtils {
    // 默认非对称加密算法
    private static final String DEFAULT_ASYM_ALGORITHM = "RSA";
    // 非对称加密签名算法
    private static final String SIGN_ALGORITHM = "RSA";
    // 默认对称加密算法
    private static final String DEFAULT_SYMM_ALGORITHM = "AES";
    // 默认对称加密填充
    private static final String DEFAULT_SYMM_PADDING = "AES/CBC/PKCS5Padding";

    /**
     * 非对称加密
     * @param data
     * @param algorithm
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String algorithm, PrivateKey privateKey) throws Exception {
        if (StringUtils.isEmpty(algorithm)) {
            algorithm = DEFAULT_ASYM_ALGORITHM;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 非对称加密
     * @param data
     * @param algorithm
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String algorithm, PublicKey publicKey) throws Exception{
        if (StringUtils.isEmpty(algorithm)) {
            algorithm = DEFAULT_ASYM_ALGORITHM;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 默认AES对称加密
     * @return
     */
    public static byte[] encrypt(byte[] data, String iv, String key) throws Exception{
        return encrypt(data, iv, key, DEFAULT_SYMM_ALGORITHM, DEFAULT_SYMM_PADDING);
    }

    /**
     * 对称加密
     * @return
     */
    public static byte[] encrypt(byte[] data, String iv, String key, String algorithm, String padding) throws Exception{
        IvParameterSpec ivSpec = new IvParameterSpec(Bytes.hexStringTobytes(iv));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(padding);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
        return cipher.doFinal(data);
    }

    /**
     * md5加密
     */
    public static String md5(String plainText) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static String md5(byte[] plain) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plain);
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    /**
     * 私钥签名
     * @param privateKey
     * @param data
     * @return
     */
    public static byte[] sign(PrivateKey privateKey, byte[] data) throws Exception{
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(EncryptUtils.md5(data).getBytes());
        return signature.sign();
    }

}

package cn.edu.swpu.info.college_website.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;


public class DecryptUtils {
    private static Logger LOG = LoggerFactory.getLogger(DecryptUtils.class);

    // 默认非对称加密算法
    private static final String DEFAULT_ASYM_ALGORITHM = "RSA";
    // 非对称加密签名算法
    private static final String SIGN_ALGORITHM = "RSA";
    // 默认对称加密算法
    private static final String DEFAULT_SYMM_ALGORITHM = "AES";
    // 默认对称加密填充
    private static final String DEFAULT_SYMM_PADDING = "AES/CBC/PKCS5Padding";

    public static byte[] decrypt(byte[] data, PublicKey publicKey) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_ASYM_ALGORITHM);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, PrivateKey privateKey) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_ASYM_ALGORITHM);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 默认AES对称解密
     * @return
     */
    public static byte[] decrypt(byte[] data, String iv, String key) throws Exception{
        return decrypt(data, iv, key, DEFAULT_SYMM_ALGORITHM, DEFAULT_SYMM_PADDING);
    }

    /**
     * 对称解密
     * @return
     */
    public static byte[] decrypt(byte[] data, String iv, String key, String algorithm, String padding) throws Exception{
        IvParameterSpec ivSpec = new IvParameterSpec(Bytes.hexStringTobytes(iv));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(padding);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
        return cipher.doFinal(data);
    }

    /**
     * 公钥验证签名
     * @param publicKey
     * @param signedData
     * @return
     */
    public static boolean verify(PublicKey publicKey, byte data[], byte[] signedData) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(EncryptUtils.md5(data).getBytes());
            return signature.verify(signedData);
        }
        catch (Exception ex) {
            LOG.error("验证签名异常", ex);
            return false;
        }
    }

    /**
     * RSA公钥验证签名
     * @param keyBytes
     * @param signedData
     * @return
     */
    public static boolean verify(byte[] keyBytes, byte[] data, byte[] signedData) {
        try {
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_ASYM_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

            return verify(publicKey, data, signedData);
        }
        catch (Exception ex) {
            LOG.error("验证签名异常", ex);
            return false;
        }
    }

}

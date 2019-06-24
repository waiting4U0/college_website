package cn.edu.swpu.info.college_website.common.tools;

import java.security.MessageDigest;

public class Md5Utils {
    public static String Md5(String plainText) {
        //System.out.println(plainText);
        return Md5(plainText.getBytes());
    }

    public static String Md5(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
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
            //System.out.println("result: " + buf.toString());// 32位的加密
            //System.out.println("result: " + buf.toString().substring(8, 24));
            return buf.toString();
            // 16位的加密
        } catch (Exception e) {
            return null;
        }
    }


}

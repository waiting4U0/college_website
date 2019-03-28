package cn.edu.swpu.info.college_website.common.tools;


/**
 * 将Long转换为8位字节数组或反向转换
 * 将8字字节数组转换为十六进制字符或反向转换
 *
 */
public class Bytes {
	
	public static String bytesToHexString(byte[] b,int pos) {
		if (b == null || b.length < 1) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < b.length;i++) {
            int bInt =  b[i] & 0xFF;
            String bStr = Integer.toHexString(bInt);
            if (bStr.length() < 2) {
                sb.append("0");
            }
            sb.append(bStr);
        }
        return sb.toString();

    }
	
	public static byte[] hexStringTobytes(String str) {
		if (str.length() < 2 || str.length() % 2 != 0) {
            return (new byte[0]);
        }
        byte[] ret = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i = i + 2) {
            String temp = str.substring(i, i+2);
            Integer val = Integer.valueOf(temp, 16);
            ret[i/2] = (byte)(val & 0xFF);
        }
        return ret;
	}
	
	public static byte[] longTo8Bytes(long v) {
		byte[] result = new byte[8];
		result[0] = (byte)((v >> 56) & 0xFF);
		result[1] = (byte)((v >> 48) & 0xFF);
		result[2] = (byte)((v >> 40) & 0xFF);
		result[3] = (byte)((v >> 32) & 0xFF);
		result[4] = (byte)((v >> 24) & 0xFF);
		result[5] = (byte)((v >> 16) & 0xFF);
		result[6] = (byte)((v >> 8) & 0xFF);
		result[7] = (byte)(v & 0xFF);
		
		return result;
	}
	
	public static long bytes8ToLong(byte[] b,int pos) {
		long fir = (int) (0x00000000000000FF & b[pos + 0]);
		long sec = (int) (0x00000000000000FF & b[pos + 1]);
		long thir = (int) (0x00000000000000FF & b[pos + 2]);
		long four = (int) (0x00000000000000FF & b[pos + 3]);
		long five = (int) (0x00000000000000FF & b[pos + 4]);
		long six = (int) (0x00000000000000FF & b[pos + 5]);
		long seven = (int) (0x00000000000000FF & b[pos + 6]);
		long eight = (int) (0x00000000000000FF & b[pos + 7]);
		long ret = (((fir << 56) | (sec << 48) | (thir << 40)
				| (four << 32) | (five << 24) | (six << 16) | (seven << 8) | eight) & 0xFFFFFFFFFFFFFFFFL);
		return ret;
	}

}

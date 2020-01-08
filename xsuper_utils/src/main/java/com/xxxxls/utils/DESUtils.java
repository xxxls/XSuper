package com.xxxxls.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DESUtils
 */
public class DESUtils {
    private static final String PASSWORD_CRYPT_KEY = "base_64Pass!live";
    private final static String DES = "DES";


    /**
     * 随机数据来源
     */
    @SuppressWarnings("unused")
    private final static String[] UPPERCASE = { "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "0", "Q", "W", "E", "R", "T", "Y", "U", "I", "O",
            "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C",
            "V", "B", "N", "M" };

    /**
     * 随机数据来源
     */
    @SuppressWarnings("unused")
    private final static String[] LOWERCASE = { "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "0", "q", "w", "e", "r", "t", "y", "u", "i", "o",
            "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c",
            "v", "b", "n", "m" };





    /**
     * 加密
     *
     * @param  src 			数据源
     * @param  key 			密钥，长度必须是8的倍数
     * @return byte[]		返回加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        Cipher cipher = getInstanceCipher(key, Cipher.ENCRYPT_MODE);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param src 		数据源
     * @param key 		密钥，长度必须是8的倍数
     * @return byte[]	返回解密后的原始数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        Cipher cipher = getInstanceCipher(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(src);
    }

    private static Cipher getInstanceCipher(byte[] key, int cipherMode)throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(cipherMode, securekey, sr);
        return cipher;
    }

    public static byte[] hex2byte(byte[] bytes) {
        if ((bytes.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[bytes.length / 2];
        for (int n = 0; n < bytes.length; n += 2) {
            String item = new String(bytes, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * 密码解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public final static String decryptPassword(String data) {
        if (data != null) {
            try {
                return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 密码加密
     *
     * @param password
     * @return
     * @throws Exception
     */
    public final static String encryptPassword(String password) {
        if (password != null) {
            try {
                return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 二行制转字符串
     *
     * @param bytes
     * @return
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String tmp = "";
        for (int n = 0; bytes != null && n < bytes.length; n++) {
            tmp = (Integer.toHexString(bytes[n] & 0xff));
            if (tmp.length() == 1) {
                builder.append("0").append(tmp);
            } else {
                builder.append(tmp);
            }
        }
        return builder.toString().toUpperCase();
    }

    /**
     * 二行制转页面字符串
     *
     * @param b
     * @return
     */
    public static String byte2WebHex(byte[] b) {
        return byte2Hex(b, "%");
    }

    /**
     * 二行制转字符串
     *
     * @param bytes
     * @param elide 		分隔符
     * @return
     */
    public static String byte2Hex(byte[] bytes, String elide) {
        StringBuilder sb = new StringBuilder();
        String stmp = "";
        elide = elide == null ? "" : elide;
        for (int n = 0; bytes != null && n < bytes.length; n++) {
            stmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1) {
                sb.append(elide).append("0").append(stmp);
            }else {
                sb.append(elide).append(stmp);
            }
        }
        return sb.toString().toUpperCase();
    }

    public static String getMD5(byte[] source) {
        String src = null;
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); 		// MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; 	// 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
            int k = 0; 						// 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { 	// 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
                byte byte0 = tmp[i];	 	// 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            src = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
        }
        return src;
    }

}

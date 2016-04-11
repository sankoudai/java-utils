package com.xulf.util.common;

/**
 * @author : sankoudai
 * @version : created at 2016/4/11
 */
public class BinaryUtil {
    public static String getBits(byte[] bytes, String separator) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }

        if (separator == null) {
            separator = "";
        }

        String bits = "";
        for (byte b : bytes) {
            bits += getBits(b) + separator;
        }

        return bits;
    }

    public static String getBits(byte b) {
        String bits = Integer.toBinaryString(b & 0xff);//b有可能为负数, 股获取

        //padding leading zeroes
        int paddingLen = 8 - bits.length();
        for (int i = 0; i < paddingLen; i++) {
            bits = '0' + bits;
        }

        return bits;
    }
}

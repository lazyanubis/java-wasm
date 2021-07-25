package wasm.core.numeric;

import wasm.core2.model.Dump;

import java.math.BigInteger;

import static wasm.core.util.NumberTransform.*;

/**
 * 数字接口
 */
public interface USize<T> extends Dump, Comparable<T> {

    int intValue();

    long longValue();

    boolean boolValue();


    byte[] getBytes();

    BigInteger uBidInteger();

    BigInteger sBidInteger();


    String toHexString();

    String toBinaryString();


    int clz();

    int ctz();

    int popcnt();


    /**
     * 将字符数组格式化对应长度字节数组，如果未能提供满足长度的字节，则按照最顶位填充 全0 或 全1
     */
    static byte[] of(byte[] bytes, int size) {
        if (null == bytes) { return new byte[size]; }

        assert size == 1 || size == 2 || size == 4 || size == 8;

        if (bytes.length == size) { return bytes; }

        byte[] bs = new byte[size];

        for (int i = size - 1; 0 <= i; i--) {
            int index = bytes.length - size + i; // 从后往前保存
            if (index < 0) {
                if ((bytes[0] & 0x80) != 0) { // 顶位是1
                    for (int j = 0; j <= i; j++) {
                        bs[j] = -1;
                    }
                }
                break;
            }
            bs[i] = bytes[index];
        }

        return bs;
    }

    /**
     * 将某进制字符串转换成对应长度字节数组
     */
    static byte[] of(String value, int radix, int size) {
        if (null == value) { return new byte[size]; }

        assert radix == 2 || radix == 16;
        assert size == 1 || size == 2 || size == 4 || size == 8;

        if (radix == 2) {
            assert value.matches("[0|1]*");

            if (value.length() < 8 * size) { value = zeros(8 * size - value.length()) + value; }
            byte[] bytes = new byte[size];
            int length = value.length();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = parseByteByBinary(value.substring(length - 8 * (size - i), length - 8 * (size - i - 1)));
            }
            return bytes;
        } else {
            value = value.toUpperCase();

            assert value.matches("[0-9A-F]*");

            if (value.length() < 2 * size) { value = zeros(2 * size - value.length()) + value; }
            byte[] bytes = new byte[size];
            int length = value.length();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = parseByteByHex(value.substring(length - 2 * (size - i), length - 2 * (size - i - 1)));
            }
            return bytes;
        }
    }

    /**
     * 复制数组
     */
    static byte[] copy(byte[] bytes) {
        assert null != bytes;

        byte[] bs = new byte[bytes.length];

        System.arraycopy(bytes, 0, bs, 0, bytes.length);

        return bs;
    }

    /**
     * 复制数组
     */
    static byte[] copy(byte[] bytes, int size) {
        assert null != bytes;

        byte[] bs = new byte[size];

        if (size <= bytes.length) {
            System.arraycopy(bytes, bytes.length - size, bs, 0, size);
        } else {
            System.arraycopy(bytes, 0, bs, size - bytes.length, bytes.length);
        }

        return bs;
    }

    /**
     * 转化为无符号
     */
    static BigInteger parseUBigInteger(byte[] bytes, boolean bool) {
        return new BigInteger(bool ? 1 : 0, bytes);
    }

    /**
     * 前置0计数
     */
    static int clz(byte[] bytes) {
        String v = toBinaryArray(bytes);
        int count = 0;
        for (int i = 0; i < v.length(); i++) {
            if (v.charAt(i) == '0') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * 后置0计数
     */
    static int ctz(byte[] bytes) {
        String v = toBinaryArray(bytes);
        int count = 0;
        for (int i = v.length() - 1; 0 <= i; i--) {
            if (v.charAt(i) == '0') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * 1计数
     */
    static int popcnt(byte[] bytes) {
        String v = toBinaryArray(bytes);
        int count = 0;
        for (int i = 0; i < v.length(); i++) {
            if (v.charAt(i) != '0') {
                count++;
            }
        }
        return count;
    }

}

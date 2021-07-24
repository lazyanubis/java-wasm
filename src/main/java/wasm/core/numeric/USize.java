package wasm.core.numeric;

import wasm.core.model.Dump;

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


    /**
     * 将字符数组格式化对应长度字节数组
     */
    static byte[] of(byte[] bytes, int size) {
        if (null == bytes) { return new byte[size]; }

        assert size == 1 || size == 2 || size == 4 || size == 8;

        byte[] bs = new byte[size];

        for (int i = size - 1; 0 <= i; i--) {
            int index = bytes.length - 1 - i;
            if (index < 0) { break; }
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
     * 转化为无符号
     */
    static BigInteger parseUBigInteger(byte[] bytes, boolean bool) {
        return new BigInteger(bool ? 1 : 0, bytes);
    }

}

package wasm.core.util;

import wasm.core.exception.Check;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数字变形
 */
public class NumberTransform {

    static final Map<Integer, String> ZEROS = new HashMap<>(128);
    static final Map<Integer, String> ONES  = new HashMap<>(128);
    static {
        for (int i = 0; i <= 64; i++) {
            ZEROS.put(i, Stream.generate(() -> 1).limit(i).map(j -> "0").collect(Collectors.joining()));
            ONES .put(i, Stream.generate(() -> 1).limit(i).map(j -> "1").collect(Collectors.joining()));
        }
    }

    /**
     * 取得全0字符串
     */
    public static String zeros(int length) {
        Check.require(0 <= length);
        Check.require(length <= 64);
        return ZEROS.get(length);
    }

    /**
     * 取得全1字符串
     */
    public static String ones(int length) {
        Check.require(0 <= length);
        Check.require(length <= 64);
        return ONES.get(length);
    }


    /**
     * 将Hex字符转化为对应的二进制数字
     */
    private static String parseBinaryByChar(char c) {
        switch (c) {
            case '0': return "0000";
            case '1': return "0001";
            case '2': return "0010";
            case '3': return "0011";
            case '4': return "0100";
            case '5': return "0101";
            case '6': return "0110";
            case '7': return "0111";
            case '8': return "1000";
            case '9': return "1001";
            case 'A': return "1010";
            case 'B': return "1011";
            case 'C': return "1100";
            case 'D': return "1101";
            case 'E': return "1110";
            case 'F': return "1111";
        }
        throw new RuntimeException("wrong char: " + c);
    }

    /**
     * 二进制字符解析byte
     */
    public static byte parseByteByBinary(String v) {
        Check.requireNonNull(v);
        Check.require(v.matches("[0|1]{8}"));

        if (v.charAt(0) == '0') {
            return (byte) Integer.parseInt(v, 2);
        } else {
            return (byte) Integer.parseUnsignedInt("111111111111111111111111" + v, 2);
        }
    }

    /**
     * 16进制字符解析byte
     */
    public static byte parseByteByHex(String v) {
        Check.requireNonNull(v);
        v = v.toUpperCase();
        Check.require(v.matches("[0-9A-F]{2}"));

        if (v.charAt(0) < '8') {
            return (byte) Integer.parseInt(v, 16);
        } else {
            return (byte) Integer.parseUnsignedInt("FFFFFF" + v, 16);
        }
    }

    /**
     * byte解析二进制字符
     */
    public static String toBinary(byte value) {
        String v = toHex(value);
        return parseBinaryByChar(v.charAt(0)) + parseBinaryByChar(v.charAt(1));
    }

    /**
     * byte解析16进制字符
     */
    public static String toHex(byte value) {
        String v = Integer.toHexString(value).toUpperCase();
        if (v.length() >= 2) { return v.substring(v.length() - 2); }
        if (v.length() == 1) { return "0" + v; }
        return v;
    }

    /**
     * byte数组解析成16进制字符串
     */
    public static String toHexArray(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) { sb.append(toHex(b)); }
        return sb.toString();
    }

    /**
     * byte数组解析成二进制字符串
     */
    public static String toBinaryArray(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) { sb.append(toBinary(b)); }
        return sb.toString();
    }

    /**
     * int32解析成16进制字符
     */
    public static String toHex(int value) {
        String v = Integer.toHexString(value).toUpperCase();
        return ZEROS.get(8 - v.length()) + v;
    }

    /**
     * int64解析成16进制字符
     */
    public static String toHex(long value) {
        String v = Long.toHexString(value).toUpperCase();
        return ZEROS.get(16 - v.length()) + v;
    }

    /**
     * 将大数解析为字节数组
     */
    static byte[] parse(BigInteger value, int size) {
        byte[] bytes = new byte[size];
        if (value.compareTo(BigInteger.ZERO) == 0) { return bytes; }

        StringBuilder sb = new StringBuilder(value.abs().toString(2)); // 二进制形式
        while (sb.length() < size * 8) { sb.insert(0, "0"); } // 补全0
        sb = new StringBuilder(sb.substring(sb.length() - size * 8));
        if (value.compareTo(BigInteger.ZERO) < 0) {
            // 所有位取反
            for (int i = 0; i < sb.length(); i++) {
                sb.replace(i, i + 1, sb.charAt(i) == '0' ? "1" : "0");
            }
            // 加1
            int last = 1;
            for (int i = sb.length() - 1; 0 <= i; i--) {
                int c = sb.charAt(i) == '0' ? 0 : 1; // 当前位的值
                int v = last + c;
                last = v > 1 ? 1 : 0; // 进位
                v = v % 2; // 当前位
                sb.replace(i, i + 1, v == 0 ? "0" : "1"); // 插入当前位
                if (last == 0) { break; } // 若不进位 则跳出
            }
        }

        for (int i = 0; i < size; i++) {
            bytes[i] = parseByteByBinary(sb.substring(i * 8, i * 8 + 8));
        }
        return bytes;
    }

}

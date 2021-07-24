package wasm.util;

import wasm.model2.number.U32;
import wasm.model2.number.U64;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class NumberUtil {

















    public static int clz(byte[] bytes) {
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

    public static int ctz(byte[] bytes) {
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

    public static int popcnt(byte[] bytes) {
        String v = toBinaryArray(bytes);
        int count = 0;
        for (int i = 0; i < v.length(); i++) {
            if (v.charAt(i) != '0') {
                count++;
            }
        }
        return count;
    }

    private static byte[] parse(BigInteger value, int size) {
        byte[] bytes = new byte[size];
        if (value.compareTo(BigInteger.ZERO) == 0) { return bytes; }

        StringBuilder sb = new StringBuilder(value.abs().toString(2));
        while (sb.length() < size * 8) {
            sb.insert(0, "0");
        }
        sb = new StringBuilder(sb.substring(sb.length() - size * 8));
        if (value.compareTo(BigInteger.ZERO) < 0) {
            // 取反
            for (int i = 0; i < sb.length(); i++) {
                sb.replace(i, i + 1, sb.charAt(i) == '0' ? "1" : "0");
            }
            // 加1
            int last = 1;
            for (int i = sb.length() - 1; 0 <= i; i--) {
                int c = sb.charAt(i) == '0' ? 0 : 1;
                int v = last + c;
                last = v > 1 ? 1 : 0;
                v = v % 2;
                sb.replace(i, i + 1, v == 0 ? "0" : "1");
                if (last == 0) {
                    break;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            bytes[i] = parseByteByBinary(sb.substring(i * 8, i * 8 + 8));
        }

        return bytes;
    }

    public static U32 add(U32 a, U32 b) {
        return new U32(parse(a.parseBigInteger().add(b.parseBigInteger()), 4));
    }

    public static U64 add(U64 a, U64 b) {
        return new U64(parse(a.parseBigInteger().add(b.parseBigInteger()), 8));
    }

    public static U32 sub(U32 a, U32 b) {
        return new U32(parse(a.parseBigInteger().subtract(b.parseBigInteger()), 4));
    }

    public static U64 sub(U64 a, U64 b) {
        return new U64(parse(a.parseBigInteger().subtract(b.parseBigInteger()), 8));
    }

    public static U32 mul(U32 a, U32 b) {
        return new U32(parse(a.parseBigInteger().multiply(b.parseBigInteger()), 4));
    }

    public static U64 mul(U64 a, U64 b) {
        return new U64(parse(a.parseBigInteger().multiply(b.parseBigInteger()), 8));
    }


    public static int divS(int a, int b) {
        return a / b;
    }

    public static long divS(long a, long b) {
        return a / b;
    }

    public static U32 divU(U32 a, U32 b) {
        return new U32(parse(a.parseBigInteger().divide(b.parseBigInteger()), 4));
    }

    public static U64 divU(U64 a, U64 b) {
        return new U64(parse(a.parseBigInteger().divide(b.parseBigInteger()), 8));
    }

    public static int remS(int a, int b) {
        return a % b;
    }

    public static long remS(long a, long b) {
        return a % b;
    }


    public static U32 remU(U32 a, U32 b) {
        return new U32(parse(a.parseBigInteger().remainder(b.parseBigInteger()), 4));
    }

    public static U64 remU(U64 a, U64 b) {
        return new U64(parse(a.parseBigInteger().remainder(b.parseBigInteger()), 8));
    }

    public static U32 and(U32 a, U32 b) {
        return new U32(
                (byte) (a.getBytes()[0] & b.getBytes()[0]),
                (byte) (a.getBytes()[1] & b.getBytes()[1]),
                (byte) (a.getBytes()[2] & b.getBytes()[2]),
                (byte) (a.getBytes()[3] & b.getBytes()[3])
                );
    }

    public static U64 and(U64 a, U64 b) {
        return new U64(new byte[] {
            (byte) (a.getBytes()[0] & b.getBytes()[0]),
            (byte) (a.getBytes()[1] & b.getBytes()[1]),
            (byte) (a.getBytes()[2] & b.getBytes()[2]),
            (byte) (a.getBytes()[3] & b.getBytes()[3]),
            (byte) (a.getBytes()[4] & b.getBytes()[4]),
            (byte) (a.getBytes()[5] & b.getBytes()[5]),
            (byte) (a.getBytes()[6] & b.getBytes()[6]),
            (byte) (a.getBytes()[7] & b.getBytes()[7])
        });
    }

    public static U32 or(U32 a, U32 b) {
        return new U32(
                (byte) (a.getBytes()[0] | b.getBytes()[0]),
                (byte) (a.getBytes()[1] | b.getBytes()[1]),
                (byte) (a.getBytes()[2] | b.getBytes()[2]),
                (byte) (a.getBytes()[3] | b.getBytes()[3])
        );
    }

    public static U64 or(U64 a, U64 b) {
        return new U64(new byte[] {
                (byte) (a.getBytes()[0] | b.getBytes()[0]),
                (byte) (a.getBytes()[1] | b.getBytes()[1]),
                (byte) (a.getBytes()[2] | b.getBytes()[2]),
                (byte) (a.getBytes()[3] | b.getBytes()[3]),
                (byte) (a.getBytes()[4] | b.getBytes()[4]),
                (byte) (a.getBytes()[5] | b.getBytes()[5]),
                (byte) (a.getBytes()[6] | b.getBytes()[6]),
                (byte) (a.getBytes()[7] | b.getBytes()[7])
        });
    }


    public static U32 xor(U32 a, U32 b) {
        return new U32(
                (byte) (a.getBytes()[0] ^ b.getBytes()[0]),
                (byte) (a.getBytes()[1] ^ b.getBytes()[1]),
                (byte) (a.getBytes()[2] ^ b.getBytes()[2]),
                (byte) (a.getBytes()[3] ^ b.getBytes()[3])
        );
    }

    public static U64 xor(U64 a, U64 b) {
        return new U64(new byte[] {
                (byte) (a.getBytes()[0] ^ b.getBytes()[0]),
                (byte) (a.getBytes()[1] ^ b.getBytes()[1]),
                (byte) (a.getBytes()[2] ^ b.getBytes()[2]),
                (byte) (a.getBytes()[3] ^ b.getBytes()[3]),
                (byte) (a.getBytes()[4] ^ b.getBytes()[4]),
                (byte) (a.getBytes()[5] ^ b.getBytes()[5]),
                (byte) (a.getBytes()[6] ^ b.getBytes()[6]),
                (byte) (a.getBytes()[7] ^ b.getBytes()[7])
        });
    }




    private static String zeros(int length) {
        if (length < 0) { throw new RuntimeException("what a length? " + length); }
        if (length == 0) { return ""; }
        if (length <= 64) { return ZEROS.get(length); }
        return zeros(64) + zeros(length - 64);
    }

    private static String ones(int length) {
        if (length < 0) { throw new RuntimeException("what a length? " + length); }
        if (length == 0) { return ""; }
        if (length <= 64) { return ONES.get(length); }
        return ones(64) + ones(length - 64);
    }

    public static U32 shl(U32 a, U32 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U32(v.substring(length) + zeros(length));
    }

    public static U64 shl(U64 a, U64 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U64(v.substring(length) + zeros(length));
    }

    public static U32 shrS(U32 a, U32 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U32((v.charAt(0) == '1' ? ones(length) : zeros(length))
                +  v.substring(0, v.length() - length));
    }

    public static U64 shrS(U64 a, U64 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U64((v.charAt(0) == '1' ? ones(length) : zeros(length))
                +  v.substring(0, v.length() - length));
    }

    public static U32 shrU(U32 a, U32 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U32(zeros(length) +  v.substring(0, v.length() - length));
    }

    public static U64 shrU(U64 a, U64 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U64(zeros(length) +  v.substring(0, v.length() - length));
    }

    public static U32 rotl(U32 a, U32 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U32(v.substring(length) + v.substring(0, length));
    }

    public static U64 rotl(U64 a, U64 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U64(v.substring(length) + v.substring(0, length));
    }


    public static U32 rotr(U32 a, U32 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U32(v.substring(v.length() - length) + v.substring(0, v.length() - length));
    }

    public static U64 rotr(U64 a, U64 b) {
        int length = b.parseBigInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return new U64(v.substring(v.length() - length) + v.substring(0, v.length() - length));
    }

}

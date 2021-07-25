package wasm.core.util;

import wasm.core.numeric.U32;
import wasm.core.numeric.U64;

import java.math.BigInteger;

import static wasm.core.util.NumberTransform.*;

/**
 * 数字符号工具
 */
public class NumberUtil {



    public static U32 add(U32 a, U32 b) {
        return U32.valueOfU(parse(a.sBidInteger().add(b.sBidInteger()), 4));
    }

    public static U64 add(U64 a, U64 b) {
        return U64.valueOfU(parse(a.sBidInteger().add(b.sBidInteger()), 8));
    }

    public static U32 sub(U32 a, U32 b) {
        return U32.valueOfU(parse(a.sBidInteger().subtract(b.sBidInteger()), 4));
    }

    public static U64 sub(U64 a, U64 b) {
        return U64.valueOfU(parse(a.sBidInteger().subtract(b.sBidInteger()), 8));
    }

    public static U32 mul(U32 a, U32 b) {
        return U32.valueOfU(parse(a.sBidInteger().multiply(b.sBidInteger()), 4));
    }

    public static U64 mul(U64 a, U64 b) {
        return U64.valueOfU(parse(a.sBidInteger().multiply(b.sBidInteger()), 8));
    }


    public static int divS(int a, int b) {
        return a / b;
    }

    public static long divS(long a, long b) {
        return a / b;
    }

    public static U32 divU(U32 a, U32 b) {
        return U32.valueOfU(parse(a.uBidInteger().divide(b.uBidInteger()), 4));
    }

    public static U64 divU(U64 a, U64 b) {
        return U64.valueOfU(parse(a.uBidInteger().divide(b.uBidInteger()), 8));
    }

    public static int remS(int a, int b) {
        return a % b;
    }

    public static long remS(long a, long b) {
        return a % b;
    }


    public static U32 remU(U32 a, U32 b) {
        return U32.valueOfU(parse(a.uBidInteger().remainder(b.uBidInteger()), 4));
    }

    public static U64 remU(U64 a, U64 b) {
        return U64.valueOfU(parse(a.uBidInteger().remainder(b.uBidInteger()), 8));
    }

    public static U32 and(U32 a, U32 b) {
        return U32.valueOfU(new byte[] {
            (byte) (a.getBytes()[0] & b.getBytes()[0]),
            (byte) (a.getBytes()[1] & b.getBytes()[1]),
            (byte) (a.getBytes()[2] & b.getBytes()[2]),
            (byte) (a.getBytes()[3] & b.getBytes()[3])
        });
    }

    public static U64 and(U64 a, U64 b) {
        return U64.valueOfU(new byte[] {
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
        return U32.valueOfU(new byte[] {
                (byte) (a.getBytes()[0] | b.getBytes()[0]),
                (byte) (a.getBytes()[1] | b.getBytes()[1]),
                (byte) (a.getBytes()[2] | b.getBytes()[2]),
                (byte) (a.getBytes()[3] | b.getBytes()[3])
        });
    }

    public static U64 or(U64 a, U64 b) {
        return U64.valueOfU(new byte[] {
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
        return U32.valueOfU(new byte[] {
                (byte) (a.getBytes()[0] ^ b.getBytes()[0]),
                (byte) (a.getBytes()[1] ^ b.getBytes()[1]),
                (byte) (a.getBytes()[2] ^ b.getBytes()[2]),
                (byte) (a.getBytes()[3] ^ b.getBytes()[3])
        });
    }

    public static U64 xor(U64 a, U64 b) {
        return U64.valueOfU(new byte[] {
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






    public static U32 shl(U32 a, U32 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U32.valueOf(v.substring(length) + zeros(length), 2);
    }

    public static U64 shl(U64 a, U64 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U64.valueOf(v.substring(length) + zeros(length), 2);
    }

    public static U32 shrS(U32 a, U32 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U32.valueOf((v.charAt(0) == '1' ? ones(length) : zeros(length))
                +  v.substring(0, v.length() - length), 2);
    }

    public static U64 shrS(U64 a, U64 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U64.valueOf((v.charAt(0) == '1' ? ones(length) : zeros(length))
                +  v.substring(0, v.length() - length), 2);
    }

    public static U32 shrU(U32 a, U32 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U32.valueOf(zeros(length) +  v.substring(0, v.length() - length), 2);
    }

    public static U64 shrU(U64 a, U64 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U64.valueOf(zeros(length) +  v.substring(0, v.length() - length), 2);
    }

    public static U32 rotl(U32 a, U32 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U32.valueOf(v.substring(length) + v.substring(0, length), 2);
    }

    public static U64 rotl(U64 a, U64 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U64.valueOf(v.substring(length) + v.substring(0, length), 2);
    }


    public static U32 rotr(U32 a, U32 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(32)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U32.valueOf(v.substring(v.length() - length) + v.substring(0, v.length() - length), 2);
    }

    public static U64 rotr(U64 a, U64 b) {
        int length = b.sBidInteger().remainder(BigInteger.valueOf(64)).intValue();
        String v = toBinaryArray(a.getBytes());
        return U64.valueOf(v.substring(v.length() - length) + v.substring(0, v.length() - length), 2);
    }

}

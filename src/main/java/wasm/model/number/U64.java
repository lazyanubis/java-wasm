package wasm.model.number;

import wasm.model.Dump;
import wasm.util.NumberUtil;

import java.math.BigInteger;
import java.util.Arrays;

import static wasm.util.NumberUtil.*;

public class U64 implements Dump, Comparable<U64> {

    private final byte[] bytes;


    public U64(String value) {
        assert value.length() == 64;

        this.bytes = new byte[8];

        for (int i = 0; i < 8; i++) {
            bytes[i] = parseByteByBinary(value.substring(8 * i, 8 * i + 8));
        }
    }

    public U64(long value) {
        this.bytes = new byte[8];

        String s = Long.toHexString(value).toUpperCase();
        switch (s.length()) {
            case  1: s = "000000000000000" + s; break;
            case  2: s = "00000000000000" + s; break;
            case  3: s = "0000000000000" + s; break;
            case  4: s = "000000000000" + s; break;
            case  5: s = "00000000000" + s; break;
            case  6: s = "0000000000" + s; break;
            case  7: s = "000000000" + s; break;
            case  8: s = "00000000" + s; break;
            case  9: s = "0000000" + s; break;
            case 10: s = "000000" + s; break;
            case 11: s = "00000" + s; break;
            case 12: s = "0000" + s; break;
            case 13: s = "000" + s; break;
            case 14: s = "00" + s; break;
            case 15: s = "0" + s; break;
        }

        for (int i = 0; i < 8; i++) {
            bytes[i] = parseByteByHex(s.substring(2 * i, 2 * i + 2));
        }
    }

    public U64(U32 value) {
        this.bytes = new byte[8];

        for (int i = 0; i < 4; i++) {
            this.bytes[4 + i] = value.getBytes()[i];
        }

    }

    public U64(byte[] bytes) {
        this.bytes = new byte[8];

        assert null != bytes;

        for (int i = 0; i < 8; i++) {
            this.bytes[7 - i] = (0 <= bytes.length - 1 - i) ? bytes[bytes.length - 1 - i] : 0;
        }
    }

    public U32 u32() {
        return new U32(bytes[4], bytes[5], bytes[6], bytes[7]);
    }

    public long longValue() {
        return Long.parseUnsignedLong(toHexArray(bytes), 16);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String toHexString() {
        return "0x"
                + toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3])
                + toHex(bytes[4]) + toHex(bytes[5]) + toHex(bytes[6]) + toHex(bytes[7]);
    }

    @Override
    public String toString() {
        return new BigInteger(toHexArray(bytes), 16).toString();
    }

    @Override
    public String dump() {
        return toString();
    }

    public boolean parseBool() {
        return bytes[0] != 0
                || bytes[1] != 0
                || bytes[2] != 0
                || bytes[3] != 0
                || bytes[4] != 0
                || bytes[5] != 0
                || bytes[6] != 0
                || bytes[7] != 0
                ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        U64 u64 = (U64) o;
        return Arrays.equals(bytes, u64.bytes);
    }

    public BigInteger parseBigInteger() {
        return new BigInteger(toHexArray(bytes), 16);
    }

    @Override
    public int compareTo(U64 o) {
        return parseBigInteger().compareTo(o.parseBigInteger());
    }

    public U64 clz() {
        return new U64(NumberUtil.clz(bytes));
    }

    public U64 ctz() {
        return new U64(NumberUtil.ctz(bytes));
    }

    public U64 popcnt() {
        return new U64(NumberUtil.popcnt(bytes));
    }

}

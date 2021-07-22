package wasm.model.number;

import wasm.model.Dump;

import java.math.BigInteger;
import java.util.Arrays;

import static wasm.util.NumberUtil.toHex;

public class U64 implements Dump, Comparable<U64> {

    private final byte[] bytes;


    public U64(String value) {
        assert value.length() == 64;

        this.bytes = new byte[8];

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) Integer.parseInt(value.substring(8 * i, 8 * i + 8), 2);
        }
    }

    public U64(long value) {
        this.bytes = new byte[8];

        String s = Long.toHexString(value);
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
            bytes[i] = Byte.valueOf(s.substring(2 * i, 2 * i + 2), 16);
        }
    }

    public U64(U32 value) {
        this.bytes = new byte[8];

        for (int i = 0; i < 4; i++) {
            this.bytes[4 + i] = value.getBytes()[i];
        }

    }

    public U32 u32() {
        return new U32(bytes[4], bytes[5], bytes[6], bytes[7]);
    }

    public long longValue() {
        return Long.parseUnsignedLong(
                toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3])
                + toHex(bytes[4]) + toHex(bytes[5]) + toHex(bytes[6]) + toHex(bytes[7]), 16);
    }

    public String toHexString() {
        return "0x"
                + toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3])
                + toHex(bytes[4]) + toHex(bytes[5]) + toHex(bytes[6]) + toHex(bytes[7]);
    }

    @Override
    public String toString() {
        return new BigInteger(
                toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3])
                  + toHex(bytes[4]) + toHex(bytes[5]) + toHex(bytes[6]) + toHex(bytes[7]),
                16).toString();
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

    private BigInteger parseBigInteger() {
        return new BigInteger(
                toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3])
                  + toHex(bytes[4]) + toHex(bytes[5]) + toHex(bytes[6]) + toHex(bytes[7]),
                16);
    }

    @Override
    public int compareTo(U64 o) {
        return parseBigInteger().compareTo(o.parseBigInteger());
    }

}

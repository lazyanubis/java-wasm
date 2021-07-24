package wasm.core2.numeric;

import java.math.BigInteger;
import java.util.Arrays;

import static wasm.core2.util.NumberTransform.*;

public class U64 implements USize<U64> {

    private final byte[] bytes;

    public U64(byte[] bytes) {
        this.bytes = USize.of(bytes, 8);
    }

    public U64(String value, int radix) {
        this.bytes = USize.of(value, radix, 8);
    }

    public U64(int value) {
        this.bytes = new byte[]{
            0, 0, 0, 0,
            (byte) ((value & 0xFF000000) >> 24),
            (byte) ((value & 0x00FF0000) >> 16),
            (byte) ((value & 0x0000FF00) >>  8),
            (byte) value
        };
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

    public U64(U8 value) { this(value.getBytes()); }
    public U64(U16 value) { this(value.getBytes()); }
    public U64(U32 value) { this(value.getBytes()); }
    public U64(U64 value) { this(value.bytes); }

    public static U64 valueOf(byte[] bytes) { return new U64(bytes); }
    public static U64 valueOf(String value, int radix) { return new U64(value, radix); }
    public static U64 valueOf(int value) { return new U64(value); }
    public static U64 valueOf(long value) { return new U64(value); }
    public static U64 valueOf(U8 value) { return new U64(value); }
    public static U64 valueOf(U16 value) { return new U64(value); }
    public static U64 valueOf(U32 value) { return new U64(value); }
    public static U64 valueOf(U64 value) { return new U64(value); }

    @Override
    public final int intValue() {
        return (int) Long.parseUnsignedLong(toHexArray(bytes), 16);
    }

    @Override
    public final long longValue() {
        return Long.parseUnsignedLong(toHexArray(bytes), 16);
    }

    @Override
    public final boolean boolValue() {
        return this.bytes[0] != 0
            || this.bytes[1] != 0
            || this.bytes[2] != 0
            || this.bytes[3] != 0
            || this.bytes[4] != 0
            || this.bytes[5] != 0
            || this.bytes[6] != 0
            || this.bytes[7] != 0;
    }

    @Override
    public final byte[] getBytes() {
        return USize.copy(bytes);
    }

    @Override
    public final BigInteger uBidInteger() {
        return USize.parseUBigInteger(bytes, boolValue());
    }

    @Override
    public final BigInteger sBidInteger() {
        return new BigInteger(bytes);
    }

    public final U8 u8() { return new U8(this.bytes); }
    public final U16 u16() { return new U16(this.bytes); }
    public final U32 u32() { return new U32(this.bytes); }

    @Override
    public final String toHexString() {
        return toHexArray(bytes);
    }

    @Override
    public final String toBinaryString() {
        return toBinaryArray(bytes);
    }

    @Override
    public final int clz() {
        return USize.clz(bytes);
    }

    @Override
    public final int ctz() {
        return USize.ctz(bytes);
    }

    @Override
    public final int popcnt() {
        return USize.popcnt(bytes);
    }

    @Override
    public final int compareTo(U64 o) {
        return uBidInteger().compareTo(o.uBidInteger());
    }

    @Override
    public final String dump() {
        return uBidInteger().toString();
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        U64 u64 = (U64) o;
        return Arrays.equals(bytes, u64.bytes);
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(bytes);
    }

}

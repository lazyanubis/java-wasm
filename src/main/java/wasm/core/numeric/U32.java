package wasm.core.numeric;

import java.math.BigInteger;
import java.util.Arrays;

import static wasm.core.util.NumberTransform.toBinaryArray;
import static wasm.core.util.NumberTransform.toHexArray;

public class U32 implements USize, Comparable<U32> {

    private final byte[] bytes;

    protected U32(byte[] bytes, boolean sign) {
        this.bytes = USize.of(bytes, 4, sign);
    }
    protected U32(String value, int radix) {
        this.bytes = USize.of(value, radix, 4);
    }
    protected U32(int value) {
        this.bytes = new byte[]{
            (byte) ((value & 0xFF000000) >> 24),
            (byte) ((value & 0x00FF0000) >> 16),
            (byte) ((value & 0x0000FF00) >>  8),
            (byte) value
        };
    }
    protected U32(long value) {
        this.bytes = new byte[]{
            (byte) ((value & 0xFF000000) >> 24),
            (byte) ((value & 0x00FF0000) >> 16),
            (byte) ((value & 0x0000FF00) >>  8),
            (byte) value
        };
    }
    protected U32(U8 value, boolean sign) { this(value.getBytes(), sign); }
    protected U32(U16 value, boolean sign) { this(value.getBytes(), sign); }
    protected U32(U32 value) { this.bytes = USize.copy(value.bytes); }
    protected U32(U64 value) { this.bytes = USize.copy(value.getBytes(), 4); }

    public static U32 valueOfU(byte[] bytes) { return new U32(bytes, false); }
    public static U32 valueOfS(byte[] bytes) { return new U32(bytes, true); }
    public static U32 valueOf(String value, int radix) { return new U32(value, radix); }
    public static U32 valueOf(int value) { return new U32(value); }
    public static U32 valueOf(long value) { return new U32(value); }
    public static U32 valueOfU(U8 value) { return new U32(value, false); }
    public static U32 valueOfS(U8 value) { return new U32(value, true); }
    public static U32 valueOfU(U16 value) { return new U32(value, false); }
    public static U32 valueOfS(U16 value) { return new U32(value, true); }
    public static U32 valueOf(U32 value) { return new U32(value); }
    public static U32 valueOf(U64 value) { return new U32(value); }

    public final U8 u8() { return U8.valueOf(this.bytes); }
    public final U16 u16() { return U16.valueOfU(this.bytes); }
    public final U64 u64() { return U64.valueOfU(this.bytes); }
    public final U8 s8() { return U8.valueOf(this.bytes); }
    public final U16 s16() { return U16.valueOfS(this.bytes); }
    public final U64 s64() { return U64.valueOfS(this.bytes); }

    @Override
    public final int intValue() {
        return Integer.parseUnsignedInt(toHexArray(bytes), 16);
    }

    @Override
    public final long longValue() {
        return Integer.parseUnsignedInt(toHexArray(bytes), 16);
    }

    @Override
    public final boolean boolValue() {
        return this.bytes[0] != 0
            || this.bytes[1] != 0
            || this.bytes[2] != 0
            || this.bytes[3] != 0;
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
    public final int compareTo(U32 o) {
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
        U32 u32 = (U32) o;
        return Arrays.equals(bytes, u32.bytes);
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString() {
        return dump();
    }

}

package wasm.core.numeric;

import java.math.BigInteger;
import java.util.Arrays;

import static wasm.core.util.NumberTransform.toBinaryArray;
import static wasm.core.util.NumberTransform.toHexArray;

public class U16 implements USize<U16> {

    private final byte[] bytes;

    public U16(byte[] bytes) {
        this.bytes = USize.of(bytes, 2);
    }

    public U16(String value, int radix) {
        this.bytes = USize.of(value, radix, 2);
    }

    public U16(int value) {
        this.bytes = new byte[]{
            (byte) ((value & 0xFF00) >> 8),
            (byte) value
        };
    }

    public U16(long value) {
        this.bytes = new byte[]{
            (byte) ((value & 0xFF00) >> 8),
            (byte) value
        };
    }

    public U16(U8 value) { this(value.getBytes()); }
    public U16(U16 value) { this(value.bytes); }
    public U16(U32 value) { this(value.getBytes()); }
    public U16(U64 value) { this(value.getBytes()); }

    public static U16 valueOf(byte[] bytes) { return new U16(bytes); }
    public static U16 valueOf(String value, int radix) { return new U16(value, radix); }
    public static U16 valueOf(int value) { return new U16(value); }
    public static U16 valueOf(long value) { return new U16(value); }
    public static U16 valueOf(U8 value) { return new U16(value); }
    public static U16 valueOf(U16 value) { return new U16(value); }
    public static U16 valueOf(U32 value) { return new U16(value); }
    public static U16 valueOf(U64 value) { return new U16(value); }

    @Override
    public final int intValue() {
        return this.bytes[0] << 8
             | this.bytes[1];
    }

    @Override
    public final long longValue() {
        return this.bytes[0] << 8
             | this.bytes[1];
    }

    @Override
    public final boolean boolValue() {
        return this.bytes[0] != 0
            || this.bytes[1] != 0;
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
    public final U32 u32() { return new U32(this.bytes); }
    public final U64 u64() { return new U64(this.bytes); }

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
    public final int compareTo(U16 o) {
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
        U16 u16 = (U16) o;
        return Arrays.equals(bytes, u16.bytes);
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(bytes);
    }

}

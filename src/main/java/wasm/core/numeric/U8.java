package wasm.core.numeric;

import java.math.BigInteger;
import java.util.Arrays;

import static wasm.core.util.NumberTransform.toBinaryArray;
import static wasm.core.util.NumberTransform.toHexArray;

public class U8 implements USize, Comparable<U8> {

    private final byte[] bytes;

    protected U8(byte[] bytes) { this.bytes = USize.of(bytes, 1, false); }
    protected U8(String value, int radix) { this.bytes = USize.of(value, radix, 1); }
    protected U8(int value) { this.bytes = new byte[]{ (byte) value }; }
    protected U8(long value) { this.bytes = new byte[]{ (byte) value }; }
    protected U8(U8 value) {  this.bytes = USize.copy(value.bytes); }
    protected U8(U16 value) { this.bytes = USize.copy(value.getBytes(), 1); }
    protected U8(U32 value) { this.bytes = USize.copy(value.getBytes(), 1); }
    protected U8(U64 value) { this.bytes = USize.copy(value.getBytes(), 1); }

    public static U8 valueOf(byte[] bytes) { return new U8(bytes); }
    public static U8 valueOf(String value, int radix) { return new U8(value, radix); }
    public static U8 valueOf(int value) { return new U8(value); }
    public static U8 valueOf(long value) { return new U8(value); }
    public static U8 valueOf(U8 value) { return new U8(value); }
    public static U8 valueOf(U16 value) { return new U8(value); }
    public static U8 valueOf(U32 value) { return new U8(value); }
    public static U8 valueOf(U64 value) { return new U8(value); }


    public final U16 u16() { return U16.valueOfU(this.bytes); }
    public final U32 u32() { return U32.valueOfU(this.bytes); }
    public final U64 u64() { return U64.valueOfU(this.bytes); }
    public final U16 s16() { return U16.valueOfS(this.bytes); }
    public final U32 s32() { return U32.valueOfS(this.bytes); }
    public final U64 s64() { return U64.valueOfS(this.bytes); }

    @Override
    public final int intValue() {
        return this.bytes[0];
    }

    @Override
    public final long longValue() {
        return this.bytes[0];
    }

    @Override
    public final boolean boolValue() {
        return this.bytes[0] != 0;
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
    public final int compareTo(U8 o) {
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
        U8 u8 = (U8) o;
        return Arrays.equals(bytes, u8.bytes);
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

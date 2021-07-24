package wasm.core.numeric;

import java.math.BigInteger;

import static wasm.core.util.NumberTransform.*;

public final class U8 implements USize<U8> {

    private final byte[] bytes;

    public U8(byte[] bytes) {
        this.bytes = USize.of(bytes, 1);
    }

    public U8(String value, int radix) {
        this.bytes = USize.of(value, radix, 1);
    }

    public U8(int value) {
        this.bytes = new byte[]{ (byte) value };
    }

    public U8(long value) {
        this.bytes = new byte[]{ (byte) value };
    }

    public U8(U8 value) {
        this.bytes = new byte[]{ value.bytes[0] };
    }

    public static U8 valueOf(byte[] bytes) { return new U8(bytes); }
    public static U8 valueOf(String value, int radix) { return new U8(value, radix); }
    public static U8 valueOf(int value) { return new U8(value); }
    public static U8 valueOf(long value) { return new U8(value); }
    public static U8 valueOf(U8 value) { return new U8(value); }

    @Override
    public int intValue() {
        return this.bytes[0];
    }

    @Override
    public long longValue() {
        return this.bytes[0];
    }

    @Override
    public boolean boolValue() {
        return this.bytes[0] != 0;
    }

    @Override
    public byte[] getBytes() {
        return USize.copy(bytes);
    }

    @Override
    public BigInteger uBidInteger() {
        return USize.parseUBigInteger(bytes, boolValue());
    }

    @Override
    public BigInteger sBidInteger() {
        return new BigInteger(bytes);
    }


    @Override
    public String toHexString() {
        return toHexArray(bytes);
    }

    @Override
    public String toBinaryString() {
        return toBinaryArray(bytes);
    }

    @Override
    public int compareTo(U8 o) {
        return uBidInteger().compareTo(o.uBidInteger());
    }

    @Override
    public String dump() {
        return uBidInteger().toString();
    }

}

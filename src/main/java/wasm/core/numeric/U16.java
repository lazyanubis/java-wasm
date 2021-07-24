package wasm.core.numeric;

import java.math.BigInteger;

import static wasm.core.util.NumberTransform.toBinaryArray;
import static wasm.core.util.NumberTransform.toHexArray;

public final class U16 implements USize<U16> {

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

    public U16(U16 value) {
        this.bytes = new byte[]{
            value.bytes[0],
            value.bytes[1],
        };
    }

    public static U16 valueOf(byte[] bytes) { return new U16(bytes); }
    public static U16 valueOf(String value, int radix) { return new U16(value, radix); }
    public static U16 valueOf(int value) { return new U16(value); }
    public static U16 valueOf(long value) { return new U16(value); }
    public static U16 valueOf(U16 value) { return new U16(value); }

    @Override
    public int intValue() {
        return this.bytes[0] << 8 | this.bytes[1];
    }

    @Override
    public long longValue() {
        return this.bytes[0] << 8 | this.bytes[1];
    }

    @Override
    public boolean boolValue() {
        return this.bytes[0] != 0 || this.bytes[1] != 0;
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
    public int compareTo(U16 o) {
        return uBidInteger().compareTo(o.uBidInteger());
    }

    @Override
    public String dump() {
        return uBidInteger().toString();
    }

}

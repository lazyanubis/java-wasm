package wasm.model.number;

import wasm.model.Dump;

import java.math.BigInteger;

import static wasm.util.NumberUtil.toHex;

public class U32 implements Dump {

    private final byte[] bytes;

    public U32(byte b0, byte b1, byte b2, byte b3) {
        this.bytes = new byte[4];
        this.bytes[0] = b0;
        this.bytes[1] = b1;
        this.bytes[2] = b2;
        this.bytes[3] = b3;
    }

    public U32(int value) {
        this.bytes = new byte[4];

        String s = Integer.toHexString(value).toUpperCase();
        switch (s.length()) {
            case 1: s = "0000000" + s; break;
            case 2: s = "000000" + s; break;
            case 3: s = "00000" + s; break;
            case 4: s = "0000" + s; break;
            case 5: s = "000" + s; break;
            case 6: s = "00" + s; break;
            case 7: s = "0" + s; break;
        }

        this.bytes[0] = Byte.valueOf(s.substring(0, 2), 16);
        this.bytes[1] = Byte.valueOf(s.substring(2, 4), 16);
        this.bytes[2] = Byte.valueOf(s.substring(4, 6), 16);
        this.bytes[3] = Byte.valueOf(s.substring(6, 8), 16);
    }

    public U32(U32 u32) {
        this.bytes = new byte[4];
        this.bytes[0] = u32.bytes[0];
        this.bytes[1] = u32.bytes[1];
        this.bytes[2] = u32.bytes[2];
        this.bytes[3] = u32.bytes[3];
    }

    public int intValue() {
        if ((bytes[0] & 0b10000000) != 0) {
            throw new RuntimeException("to large");
        }
        return new BigInteger(
                toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3]),
                16).intValue();
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String toHexString() {
        return "0x" + toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3]);
    }

    @Override
    public String toString() {
        return new BigInteger(
                toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3]),
                16).toString();
    }

    @Override
    public String dump() {
        return toString();
    }
}

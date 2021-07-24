package wasm.core.model.type;

import wasm.core.model.Type;

import static wasm.core.util.NumberTransform.toHex;

public class NumberType implements Type {

    private final byte value;

    private final String name;

    private NumberType(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    private static final NumberType EMPTY = new NumberType((byte) 0x40, "nil");
    private static final NumberType I32 = new NumberType((byte) 0x7F, "i32");
    private static final NumberType I64 = new NumberType((byte) 0x7E, "i64");
//    public static final NumberType F32 = new NumberType((byte) 0x7D, "f32");
//    public static final NumberType F64 = new NumberType((byte) 0x7C, "f64");

    public static NumberType of(byte value) {
        switch (value) {
            case 0x40: return EMPTY;

            case 0x7F: return I32;
            case 0x7E: return I64;
//            case 0x7D: System.err.println("float number warning: " + F32.name); return F32;
//            case 0x7C: System.err.println("float number warning: " + F64.name); return F64;
//            case 0x7D:
//            case 0x7C:
//                throw new RuntimeException("float number is forbid");
        }
        throw new RuntimeException("wrong value: 0x" + toHex(value));
    }

    @Override
    public byte value() {
        return value;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String dump() {
        return "0x" + toHex(value) + " " + name;
    }

    @Override
    public String toString() {
        return "NumberType{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}

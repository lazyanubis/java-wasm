package wasm.model.type;

import wasm.model.Dump;

import static wasm.util.NumberUtil.toHex;

public class ValueType implements Type, Dump {

    private final Type value;

    public ValueType(Type value) {
        this.value = value;
    }

    private static final ValueType EMPTY = new ValueType(NumberType.of((byte) 0x40));
    private static final ValueType I32 = new ValueType(NumberType.of((byte) 0x7F));
    private static final ValueType I64 = new ValueType(NumberType.of((byte) 0x7E));
    private static final ValueType F32 = new ValueType(NumberType.of((byte) 0x7D));
    private static final ValueType F64 = new ValueType(NumberType.of((byte) 0x7C));

    private static final ValueType FUNCTION_REFERENCE = new ValueType(ReferenceType.of((byte) 0x70));
    private static final ValueType EXTERN_REFERENCE   = new ValueType(ReferenceType.of((byte) 0x6F));

    public static ValueType of(byte value) {
        switch (value) {
            case 0x40: return EMPTY;

            case 0x7F: return I32;
            case 0x7E: return I64;
            case 0x7D: return F32;
            case 0x7C: return F64;

            case 0x70: return FUNCTION_REFERENCE;
            case 0x6F: return EXTERN_REFERENCE;
        }
        throw new RuntimeException("wrong value: 0x" + toHex(value));
    }

    public byte value() {
        return value.value();
    }

    public String name() {
        return value.name();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String dump() {
        return value.dump();
    }
}

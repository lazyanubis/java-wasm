package wasm.core.model.type;

import wasm.core.model.Dump;
import wasm.core.model.Type;

import static wasm.core.util.NumberTransform.toHex;

public class ValueType implements Type, Dump {

    private final Type value;

    public ValueType(Type value) {
        this.value = value;
    }

    public static final ValueType EMPTY = new ValueType(NumberType.of((byte) 0x40));
    public static final ValueType I32 = new ValueType(NumberType.of((byte) 0x7F));
    public static final ValueType I64 = new ValueType(NumberType.of((byte) 0x7E));
//    public static final ValueType F32 = new ValueType(NumberType.F32);
//    public static final ValueType F64 = new ValueType(NumberType.F64);

    public static final ValueType FUNCTION_REFERENCE = new ValueType(ReferenceType.of((byte) 0x70));
    public static final ValueType EXTERN_REFERENCE   = new ValueType(ReferenceType.of((byte) 0x6F));

    public static ValueType of(byte value) {
        switch (value) {
            case 0x40: return EMPTY;

            case 0x7F: return I32;
            case 0x7E: return I64;
//            case 0x7D: return F32;
//            case 0x7C: return F64;

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

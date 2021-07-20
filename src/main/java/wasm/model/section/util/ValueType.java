package wasm.model.section.util;

public enum ValueType {

    Int32(0x7F),
    Int64(0x7E),
    Float32(0x7D),
    Float64(0x7C),

    Function(0x60), // 类型段中固定的函数标识
    FunctionReference(0x70);

    public final byte value;

    ValueType(int value) {
        this.value = (byte) value;
    }


    public static ValueType valueOf(byte value) {
        for (ValueType type : ValueType.values()) {
            if (value == type.value) {
                return type;
            }
        }
        throw new RuntimeException("wrong value for ValueType");
    }

}

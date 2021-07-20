package wasm.model.section.util;

public enum VariableType {

    CONST(0),

    MUTABLE(1);

    public final byte value;

    VariableType(int value) {
        this.value = (byte) value;
    }

    public static VariableType valueOf(byte value) {
        for (VariableType type : VariableType.values()) {
            if (value == type.value) {
                return type;
            }
        }
        throw new RuntimeException("wrong value for VariableType");
    }

}

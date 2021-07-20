package wasm.model.section.util;

public enum ImportType {
    FUNCTION(0),

    TABLE(1),

    MEMORY(2),

    GLOBAL(3);

    public final byte value;

    ImportType(int value) {
        this.value = (byte) value;
    }

    public static ImportType valueOf(byte value) {
        for (ImportType type : ImportType.values()) {
            if (value == type.value) {
                return type;
            }
        }
        throw new RuntimeException("wrong value for ImportType");
    }

}

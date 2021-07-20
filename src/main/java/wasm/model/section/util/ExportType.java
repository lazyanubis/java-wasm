package wasm.model.section.util;

public enum ExportType {
    FUNCTION(0),

    TABLE(1),

    MEMORY(2),

    GLOBAL(3);

    public final byte value;

    ExportType(int value) {
        this.value = (byte) value;
    }

    public static ExportType valueOf(byte value) {
        for (ExportType type : ExportType.values()) {
            if (value == type.value) {
                return type;
            }
        }
        throw new RuntimeException("wrong value for ExportType");
    }

}

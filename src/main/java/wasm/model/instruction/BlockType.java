package wasm.model.instruction;

public enum BlockType {

    Int32(-1),

    Int64(-2),

    Float32(-3),

    Float64(-4),

    Empty(-64);

    public final byte value;

    BlockType(int value) {
        this.value = (byte) value;
    }

    public static BlockType valueOf(byte value) {
        for (BlockType type : BlockType.values()) {
            if (value == type.value) {
                return type;
            }
        }
        throw new RuntimeException("wrong value for BlockType");
    }

}

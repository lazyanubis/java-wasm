package wasm.model.type;

public class BlockType implements Type {

    public final ValueType valueType;

    public final long s32;

    public BlockType(ValueType valueType, long s32) {
        this.valueType = valueType;
        this.s32 = s32;
    }

    @Override
    public byte value() {
        return null == valueType ? 0 : valueType.value();
    }

    @Override
    public String name() {
        return null == valueType ? "s33:" + s32 : valueType.name();
    }

    @Override
    public String dump() {
        return name();
    }
}

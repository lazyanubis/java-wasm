package wasm.model.type;

public class BlockType implements Type {

    public final ValueType valueType;

    public final long s33;

    public BlockType(ValueType valueType, long s33) {
        this.valueType = valueType;
        this.s33 = s33;
    }

    @Override
    public byte value() {
        return null == valueType ? 0 : valueType.value();
    }

    @Override
    public String name() {
        return null == valueType ? "s33:" + s33 : valueType.name();
    }

    @Override
    public String dump() {
        return name();
    }
}

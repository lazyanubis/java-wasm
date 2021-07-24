package wasm.core.model.type;

import wasm.core.model.Type;

public class BlockType implements Type {

    public final ValueType type;

    public final long s33;

    public BlockType(ValueType type, long s33) {
        this.type = type;
        this.s33 = s33;
    }

    @Override
    public byte value() {
        return null == type ? 0 : type.value();
    }

    @Override
    public String name() {
        return null == type ? "s33:" + s33 : type.name();
    }

    @Override
    public String dump() {
        return name();
    }
}

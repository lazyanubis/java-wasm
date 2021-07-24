package wasm.core2.instance;

import wasm.core3.numeric.U64;
import wasm.core2.model.type.GlobalType;
import wasm.core2.model.type.MutableType;
import wasm.core2.structure.Global;

public class GlobalInstance implements Global {

    public final GlobalType type;

    public U64 value;

    public GlobalInstance(GlobalType type, U64 value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public GlobalType type() {
        return type;
    }

    @Override
    public U64 get() {
        return value;
    }

    @Override
    public void set(U64 value) {
        if (type.mutable != MutableType.MUTABLE) {
            throw new RuntimeException("immutable global");
        }
        this.value = value;
    }

}

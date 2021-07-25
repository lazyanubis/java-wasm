package wasm.core2.instance;

import wasm.core.numeric.USize;
import wasm.core2.model.type.GlobalType;
import wasm.core2.model.type.MutableType;
import wasm.core2.structure.Global;

public class GlobalInstance implements Global {

    public final GlobalType type;

    public USize value;

    public GlobalInstance(GlobalType type, USize value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public GlobalType type() {
        return type;
    }

    @Override
    public USize get() {
        return value;
    }

    @Override
    public void set(USize value) {
        if (type.mutable != MutableType.MUTABLE) {
            throw new RuntimeException("immutable global");
        }
        this.value = value;
    }

}

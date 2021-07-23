package wasm.core;

import wasm.model.number.U64;
import wasm.model.type.GlobalType;
import wasm.model.type.MutableType;

public class GlobalVariable {

    public final GlobalType type;

    public U64 value;

    public GlobalVariable(GlobalType type, U64 value) {
        this.type = type;
        this.value = value;
    }

    public U64 getU64() {
        return value;
    }

    public void setU64(U64 value) {
        if (type.mutableType != MutableType.MUTABLE) {
            throw new RuntimeException("immutable global");
        }
        this.value = value;
    }


}

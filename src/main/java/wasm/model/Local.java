package wasm.model;

import wasm.model.number.U32;
import wasm.model.type.ValueType;

public class Local {

    public U32 n;

    public ValueType type;

    public Local(U32 n, ValueType type) {
        this.n = n;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Local{" +
                "n=" + n +
                ", type=" + type +
                '}';
    }
}

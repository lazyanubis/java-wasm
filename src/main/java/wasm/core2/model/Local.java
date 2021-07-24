package wasm.core2.model;

import wasm.core2.numeric.U32;
import wasm.core2.model.type.ValueType;

public class Local {

    public final U32 n;           // 本地变量长度
    public final ValueType type;  // 本地变量类型

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

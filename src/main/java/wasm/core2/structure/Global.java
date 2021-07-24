package wasm.core2.structure;

import wasm.core2.model.type.GlobalType;
import wasm.core.numeric.U64;

public interface Global {

    /**
     * 全局数据类型
     */
    GlobalType type();

    /**
     * 获取全局参数
     */
    U64 get();

    /**
     * 设置全局参数
     */
    void set(U64 value);

}

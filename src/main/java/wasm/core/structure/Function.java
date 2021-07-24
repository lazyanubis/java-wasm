package wasm.core.structure;

import wasm.core.model.section.FunctionType;
import wasm.core.numeric.U64;

public interface Function {

    /**
     * 函数签名
     */
    FunctionType type();

    /**
     * 调用函数
     */
    U64 call(U64... args);

}

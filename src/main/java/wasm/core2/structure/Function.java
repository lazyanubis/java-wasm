package wasm.core2.structure;

import wasm.core2.model.section.CodeSection;
import wasm.core2.model.section.FunctionType;
import wasm.core.numeric.U64;

public interface Function {

    /**
     * 函数签名
     */
    FunctionType type();

    /**
     * 调用函数
     */
    U64[] call(U64... args);

    /**
     * 是否内部函数
     */
    default boolean isInternal() { return false; }

    /**
     * 获取内部函数具体内容
     */
    default CodeSection getCodeSection() { return null; }

}

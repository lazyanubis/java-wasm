package wasm.core2.structure;

import wasm.core2.model.section.CodeSection;
import wasm.core2.model.section.FunctionType;
import wasm.core2.numeric.U64;

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
    boolean isInternal();

    /**
     * 获取内部函数具体内容
     */
    CodeSection getCodeSection();

}

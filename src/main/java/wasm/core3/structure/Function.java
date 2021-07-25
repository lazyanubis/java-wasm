package wasm.core3.structure;

import wasm.core.numeric.USize;
import wasm.core2.model.section.CodeSection;
import wasm.core2.model.section.FunctionType;

public interface Function {

    /**
     * 函数签名
     */
    FunctionType type();

    /**
     * 调用函数
     */
    USize[] call(USize... args);

    /**
     * 是否内部函数
     */
    default boolean isInternal() { return false; }

    /**
     * 获取内部函数具体内容
     */
    default CodeSection getCodeSection() { return null; }

}

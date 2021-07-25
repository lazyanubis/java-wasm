package wasm.core.structure;

import wasm.core.model.section.MemoryType;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;

public interface Memory {

    /**
     * 内存类型
     */
    MemoryType type();

    /**
     * 内存页数
     */
    U32 size();

    /**
     * 内存扩页
     */
    U32 grow(U32 grow);

    /**
     * 读取内存 读取后注意小端法
     */
    void read(U64 offset, byte[] buffer);

    /**
     * 写入内存 写入前注意小端法
     */
    void write(U64 offset, byte[] data);

}

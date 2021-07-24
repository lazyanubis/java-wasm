package wasm.core.model.index;

import wasm.core.numeric.U32;

public class MemoryIndex extends U32 {

    private MemoryIndex(U32 u32) {
        super(u32);
    }

    private MemoryIndex(int value) {
        super(value);
    }

    public static MemoryIndex of(U32 value) { return MemoryIndex.of(value); }

    public static MemoryIndex of(int value) { return MemoryIndex.of(value); }

    public String dump(int index) {
        return "mem[" + index + "]: " + "value=" + super.toString();
    }

}

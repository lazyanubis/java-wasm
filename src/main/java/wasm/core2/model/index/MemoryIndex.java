package wasm.core2.model.index;

import wasm.core2.numeric.U32;

public class MemoryIndex extends U32 {

    public MemoryIndex(U32 u32) {
        super(u32);
    }

    public MemoryIndex(int value) {
        super(value);
    }

    public static MemoryIndex of(int value) { return new MemoryIndex(value); }

    public String dump(int index) {
        return "mem[" + index + "]: " + "value=" + super.toString();
    }

}

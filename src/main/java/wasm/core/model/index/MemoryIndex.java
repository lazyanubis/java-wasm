package wasm.core.model.index;

import wasm.core.numeric.U32;

public class MemoryIndex extends U32 {

    public MemoryIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "mem[" + index + "]: " + "value=" + super.toString();
    }

}

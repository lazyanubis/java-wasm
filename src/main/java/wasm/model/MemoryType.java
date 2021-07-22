package wasm.model;

import wasm.model.number.U32;
import wasm.model.tag.LimitsTag;

public class MemoryType extends Limits {

    public MemoryType(LimitsTag tag, U32 min, U32 max) {
        super(tag, min, max);
    }

    public String dump(int index) {
        return "memory[" + index + "]: " + dump();
    }

}

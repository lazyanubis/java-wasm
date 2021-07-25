package wasm.core.model.section;

import wasm.core.model.Limits;
import wasm.core.model.tag.LimitsTag;
import wasm.core.numeric.U32;

public class MemoryType extends Limits {

    public MemoryType(LimitsTag tag, U32 min, U32 max) {
        super(tag, min, max);
    }

    public String dump(int index) {
        return "memory[" + index + "]: " + dump();
    }

}

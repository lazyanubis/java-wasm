package wasm.core2.model.section;

import wasm.core2.model.Limits;
import wasm.core3.model.tag.LimitsTag;
import wasm.core.numeric.U32;

public class MemoryType extends Limits {

    public MemoryType(LimitsTag tag, U32 min, U32 max) {
        super(tag, min, max);
    }

    public String dump(int index) {
        return "memory[" + index + "]: " + dump();
    }

}

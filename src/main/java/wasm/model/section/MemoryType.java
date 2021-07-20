package wasm.model.section;

import wasm.model.section.util.Limit;
import wasm.model.section.util.LimitTag;
import wasm.model.section.util.Uint32;

public class MemoryType extends Limit {
    public MemoryType(LimitTag tag, Uint32 min, Uint32 max) {
        super(tag, min, max);
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("memory[").append(index).append("]: ").append(dump());

        return sb.toString();
    }
}

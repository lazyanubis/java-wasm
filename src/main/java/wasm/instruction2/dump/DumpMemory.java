package wasm.instruction2.dump;

import wasm.core2.model.Dump;
import wasm.core3.numeric.U32;

public class DumpMemory implements Dump {

    private final U32 align;
    private final U32 offset;

    public DumpMemory(U32 align, U32 offset) {
        this.align = align;
        this.offset = offset;
    }

    public U32 getOffset() {
        return offset;
    }

    @Override
    public String dump() {
        return String.format("{align: %s, offset: %s}", align.dump(), offset.dump());
    }

    @Override
    public String toString() {
        return dump();
    }

}

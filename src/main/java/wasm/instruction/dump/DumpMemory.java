package wasm.instruction.dump;

import wasm.model.Dump;
import wasm.model.number.U32;

public class DumpMemory implements Dump {

    private final U32 a;
    private final U32 o;

    public DumpMemory(U32 a, U32 o) {
        this.a = a;
        this.o = o;
    }

    @Override
    public String dump() {
        return String.format("{align: %s, offset: %s}", a.toString(), o.toString());
    }

    @Override
    public String toString() {
        return String.format("{align: %s, offset: %s}", a.toString(), o.toString());
    }
}

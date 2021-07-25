package wasm.core.instruction.dump;

import wasm.core.model.Dump;

public class DumpI32 implements Dump {

    public int value;

    public DumpI32(int value) {
        this.value = value;
    }

    @Override
    public String dump() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

package wasm.model.instruction.util;

import wasm.model.section.util.Dump;

public class DumpInt32 implements Dump {

    public int value;

    public DumpInt32(int value) {
        this.value = value;
    }

    @Override
    public String dump() {
        return Integer.toHexString(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

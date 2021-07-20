package wasm.model.instruction.util;

import wasm.model.section.util.Dump;

public class DumpInt64 implements Dump {

    public long value;

    public DumpInt64(long value) {
        this.value = value;
    }

    @Override
    public String dump() {
        return Long.toHexString(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

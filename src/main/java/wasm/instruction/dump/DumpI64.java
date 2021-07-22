package wasm.instruction.dump;

import wasm.model.Dump;

import static wasm.util.NumberUtil.toHex;

public class DumpI64 implements Dump {

    public long value;

    public DumpI64(long value) {
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

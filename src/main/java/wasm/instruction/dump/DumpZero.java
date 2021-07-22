package wasm.model2.instruction.util;

import wasm.model.Dump;

import static wasm.util.NumberUtil.toHex;

public class DumpZero implements Dump {

    public byte value;

    public DumpZero(byte value) {
        this.value = value;
    }

    @Override
    public String dump() {
        return toHex(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

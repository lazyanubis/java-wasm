package wasm.instruction.dump;

import wasm.model.Dump;

import static wasm.util.NumberUtil.toHex;

public class DumpByte implements Dump {

    public byte value;

    public DumpByte(byte value) {
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

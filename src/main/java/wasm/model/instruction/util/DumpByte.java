package wasm.model.instruction.util;

import wasm.model.section.util.Dump;

import static wasm.model.instruction.Instruction.toHex;

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

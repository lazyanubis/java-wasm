package wasm.model.instruction.util;

import wasm.model.section.util.Dump;
import wasm.model.section.util.Uint32;

import static wasm.model.instruction.Instruction.toHex;

public class DumpUint32 implements Dump {

    public Uint32 value;

    public DumpUint32(Uint32 value) {
        this.value = value;
    }

    @Override
    public String dump() {
        return toHex((byte) Integer.parseInt(value.getValue().substring(24), 2)) + " "
        + toHex((byte) Integer.parseInt(value.getValue().substring(16, 24), 2)) + " "
        + toHex((byte) Integer.parseInt(value.getValue().substring(8, 16), 2)) + " "
        + toHex((byte) Integer.parseInt(value.getValue().substring(0, 8), 2));
    }

    @Override
    public String toString() {
        return String.valueOf(value.value());
    }
}

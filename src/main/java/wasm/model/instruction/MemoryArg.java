package wasm.model.instruction;

import wasm.model.section.util.Dump;
import wasm.model.section.util.Uint32;

import static wasm.model.instruction.Instruction.toHex;

public class MemoryArg implements Dump {

    public Uint32 align;

    public Uint32 offset;

    @Override
    public String dump() {
        return toHex((byte)Integer.parseInt(align.getValue(), 2))
                + " " + toHex((byte)Integer.parseInt(offset.getValue(), 2));
    }

    @Override
    public String toString() {
        return Integer.parseInt(align.getValue(), 2) + " " + Integer.parseInt(offset.getValue(), 2);
    }
}

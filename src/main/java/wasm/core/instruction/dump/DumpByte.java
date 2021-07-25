package wasm.core.instruction.dump;

import wasm.core.model.Dump;

import static wasm.core.util.NumberTransform.toHex;


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

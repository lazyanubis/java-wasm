package wasm.model.instruction.util;

import wasm.model.section.util.Dump;

public class DumpFloat32 implements Dump {

    public float value;

    public DumpFloat32(float value) {
        this.value = value;
    }

    @Override
    public String dump() {
        return Float.toHexString(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

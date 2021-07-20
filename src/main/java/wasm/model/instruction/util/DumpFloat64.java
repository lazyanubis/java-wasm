package wasm.model.instruction.util;

import wasm.model.section.util.Dump;

public class DumpFloat64 implements Dump {

    public double value;

    public DumpFloat64(double value) {
        this.value = value;
    }

    @Override
    public String dump() {
        return Double.toHexString(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

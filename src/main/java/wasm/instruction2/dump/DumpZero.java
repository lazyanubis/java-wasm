package wasm.instruction2.dump;

import wasm.core2.model.Dump;

import static wasm.core2.util.NumberTransform.toHex;

public class DumpZero implements Dump {

    public byte value;

    public DumpZero(byte value) {
        assert value == 0x00;
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

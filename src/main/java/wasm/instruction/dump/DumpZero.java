package wasm.instruction.dump;

import wasm.model.Dump;

import static wasm.util.NumberUtil.toHex;

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

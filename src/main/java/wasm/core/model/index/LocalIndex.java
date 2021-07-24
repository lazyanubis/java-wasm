package wasm.core.model.index;

import wasm.core.numeric.U32;

public class LocalIndex extends U32 {

    protected LocalIndex(U32 u32) {
        super(u32);
    }

    public static LocalIndex of(U32 value) { return new LocalIndex(value); }

    public String dump(int index) {
        return "local[" + index + "]: " + "value=" + super.toString();
    }

}

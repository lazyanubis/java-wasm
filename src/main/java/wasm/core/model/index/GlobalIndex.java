package wasm.core.model.index;

import wasm.core.numeric.U32;

public class GlobalIndex extends U32 {

    private GlobalIndex(U32 u32) {
        super(u32);
    }

    public GlobalIndex(int value) {
        super(value);
    }

    public static GlobalIndex of(U32 value) { return new GlobalIndex(value); }

    public static GlobalIndex of(int value) { return new GlobalIndex(value); }

    public String dump(int index) {
        return "global[" + index + "]: " + "value=" + super.toString();
    }

}

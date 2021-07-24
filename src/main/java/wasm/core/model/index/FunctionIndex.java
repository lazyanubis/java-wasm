package wasm.core.model.index;

import wasm.core.numeric.U32;

public class FunctionIndex extends U32 {

    public FunctionIndex(U32 u32) {
        super(u32);
    }

    public FunctionIndex(int value) {
        super(value);
    }

    public static FunctionIndex of(int value) { return new FunctionIndex(value); }

    public String dump(int index) {
        return "FunctionIndex[" + index + "]: " + "value=" + super.toString();
    }

}

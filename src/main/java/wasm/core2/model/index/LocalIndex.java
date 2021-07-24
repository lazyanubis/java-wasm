package wasm.core2.model.index;

import wasm.core2.numeric.U32;

public class LocalIndex extends U32 {

    public LocalIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "local[" + index + "]: " + "value=" + super.toString();
    }

}

package wasm.core.model.index;

import wasm.core.numeric.U32;

public class LocalIndex extends U32 {

    public LocalIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "local[" + index + "]: " + "value=" + super.toString();
    }

}

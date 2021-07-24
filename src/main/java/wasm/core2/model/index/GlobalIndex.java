package wasm.core2.model.index;

import wasm.core.numeric.U32;

public class GlobalIndex extends U32 {

    public GlobalIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "global[" + index + "]: " + "value=" + super.toString();
    }

}

package wasm.core2.model.index;

import wasm.core.numeric.U32;

public class DataIndex extends U32 {

    public DataIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "data[" + index + "]: " + "value=" + super.toString();
    }

}

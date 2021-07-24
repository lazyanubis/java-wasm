package wasm.core2.model.index;

import wasm.core2.numeric.U32;

public class DataCountIndex extends U32 {

    public DataCountIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "DataCountIndex[" + index + "]: " + "value=" + super.toString();
    }

}

package wasm.core.model.index;

import wasm.core.numeric.U32;

public class TableIndex extends U32 {

    public TableIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "table[" + index + "]: " + "value=" + super.toString();
    }

}

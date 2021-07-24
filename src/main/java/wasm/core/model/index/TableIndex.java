package wasm.core.model.index;

import wasm.core.numeric.U32;

public class TableIndex extends U32 {

    public TableIndex(U32 u32) {
        super(u32);
    }

    public TableIndex(int value) {
        super(value);
    }

    public static TableIndex of(int value) { return new TableIndex(value); }

    public String dump(int index) {
        return "table[" + index + "]: " + "value=" + super.toString();
    }

}

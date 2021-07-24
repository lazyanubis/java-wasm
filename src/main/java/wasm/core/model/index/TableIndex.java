package wasm.core.model.index;

import wasm.core.numeric.U32;

public class TableIndex extends U32 {

    private TableIndex(U32 u32) {
        super(u32);
    }

    private TableIndex(int value) {
        super(value);
    }

    public static TableIndex of(U32 value) { return new TableIndex(value); }

    public static TableIndex of(int value) { return new TableIndex(value); }

    public String dump(int index) {
        return "table[" + index + "]: " + "value=" + super.toString();
    }

}

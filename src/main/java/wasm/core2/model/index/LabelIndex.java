package wasm.core2.model.index;

import wasm.core.numeric.U32;

public class LabelIndex extends U32 {

    public LabelIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "label[" + index + "]: " + "value=" + super.toString();
    }

}

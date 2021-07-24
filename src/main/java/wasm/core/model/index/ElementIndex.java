package wasm.core.model.index;

import wasm.core.numeric.U32;

public class ElementIndex extends U32 {
    
    public ElementIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "elem[" + index + "]: " + "value=" + super.toString();
    }

}

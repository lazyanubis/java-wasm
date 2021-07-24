package wasm.core.model.index;

import wasm.core.numeric.U32;

public class ElementIndex extends U32 {
    
    private ElementIndex(U32 u32) {
        super(u32);
    }

    public static ElementIndex of(U32 value) { return new ElementIndex(value); }

    public String dump(int index) {
        return "elem[" + index + "]: " + "value=" + super.toString();
    }

}

package wasm.core2.model.index;

import wasm.core.numeric.U32;

public class TypeIndex extends U32 {

    public TypeIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "func[" + index + "]: " + "value=" + super.toString();
    }

}

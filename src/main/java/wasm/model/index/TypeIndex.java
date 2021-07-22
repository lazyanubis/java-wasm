package wasm.model.index;

import wasm.model.number.U32;

public class TypeIndex extends U32 {

    public TypeIndex(U32 u32) {
        super(u32);
    }

    public String dump(int index) {
        return "func[" + index + "]: " + "sig=" + super.toString();
    }

}

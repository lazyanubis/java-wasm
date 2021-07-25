package wasm.core.model.index;

import wasm.core.numeric.U32;

public class TypeIndex extends U32 {

    private TypeIndex(U32 u32) {
        super(u32);
    }

    public static TypeIndex of(U32 value) { return new TypeIndex(value); }

    public String dump(int index) {
        return "func[" + index + "]: " + "value=" + super.dump();
    }

}

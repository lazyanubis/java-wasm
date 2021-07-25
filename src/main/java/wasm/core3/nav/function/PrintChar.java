package wasm.core3.nav.function;

import wasm.core3.model.tag.FunctionTypeTag;
import wasm.core.numeric.U64;
import wasm.core2.model.section.FunctionType;
import wasm.core2.model.type.ValueType;
import wasm.core3.structure.Function;

class PrintChar implements Function {

    @Override
    public FunctionType type() {
        return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I64 }, new ValueType[]{});
    }

    @Override
    public U64[] call(U64... args) {
        int v = args[0].intValue();
        System.out.print(new String(new byte[]{(byte) v}));
        return new U64[0];
    }
}

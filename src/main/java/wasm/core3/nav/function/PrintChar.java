package wasm.core3.nav.function;

import wasm.core.numeric.USize;
import wasm.core2.model.section.FunctionType;
import wasm.core2.model.type.ValueType;
import wasm.core3.model.tag.FunctionTypeTag;
import wasm.core3.structure.Function;

class PrintChar implements Function {

    @Override
    public FunctionType type() {
        return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I64 }, new ValueType[]{});
    }

    @Override
    public USize[] call(USize... args) {
        int v = args[0].intValue();
        System.out.print(new String(new byte[]{(byte) v}));
        return new USize[0];
    }
}

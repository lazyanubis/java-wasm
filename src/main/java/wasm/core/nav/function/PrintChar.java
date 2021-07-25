package wasm.core.nav.function;

import wasm.core.numeric.USize;
import wasm.core.model.section.FunctionType;
import wasm.core.model.type.ValueType;
import wasm.core.model.tag.FunctionTypeTag;
import wasm.core.structure.Function;

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

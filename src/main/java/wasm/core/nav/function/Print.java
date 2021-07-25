package wasm.core.nav.function;

import wasm.core.model.section.FunctionType;
import wasm.core.model.tag.FunctionTypeTag;
import wasm.core.model.type.ValueType;
import wasm.core.numeric.USize;
import wasm.core.structure.Function;

class Print {

    static class PrintChar implements Function {
        @Override
        public FunctionType type() {
            return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I32 }, new ValueType[]{});
        }

        @Override
        public USize[] call(USize... args) {
            int v = args[0].intValue();
            System.out.print(new String(new byte[]{(byte) v}));
            return new USize[0];
        }
    }

    static class PrintI64 implements Function {
        @Override
        public FunctionType type() {
            return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I64 }, new ValueType[]{});
        }

        @Override
        public USize[] call(USize... args) {
            System.out.print(args[0].longValue());
            return new USize[0];
        }
    }

    static class PrintI32 implements Function {
        @Override
        public FunctionType type() {
            return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I32 }, new ValueType[]{});
        }

        @Override
        public USize[] call(USize... args) {
            System.out.print(args[0].intValue());
            return new USize[0];
        }
    }

}

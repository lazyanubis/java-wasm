package wasm.instruction2.reference;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.index.FunctionIndex;

public class RefFunc implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readFunctionIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof FunctionIndex;

        FunctionIndex a = (FunctionIndex) args;

        Operate.super.operate(mi, args);
    }
}

package wasm.instruction.reference;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.FunctionIndex;

public class RefFunc implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readFunctionIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof FunctionIndex;

        FunctionIndex a = (FunctionIndex) args;

        Operate.super.operate(vm, args);
    }
}

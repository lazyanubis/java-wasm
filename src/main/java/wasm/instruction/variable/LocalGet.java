package wasm.instruction.variable;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.LocalIndex;

public class LocalGet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLocalIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof LocalIndex;

        LocalIndex a = (LocalIndex) args;

        Operate.super.operate(vm, args);
    }

}

package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class Nop implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) { }

}

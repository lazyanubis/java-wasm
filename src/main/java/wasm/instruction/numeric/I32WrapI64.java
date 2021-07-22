package wasm.instruction.numeric;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class I32WrapI64 implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        vm.pushU32(vm.popU64().u32());
    }
}

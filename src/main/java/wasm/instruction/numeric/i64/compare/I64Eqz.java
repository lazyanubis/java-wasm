package wasm.instruction.numeric.i64.compare;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class I64Eqz implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        vm.pushBool(vm.popU64().parseBool());
    }

}

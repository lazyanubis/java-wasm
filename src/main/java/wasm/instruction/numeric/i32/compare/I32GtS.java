package wasm.instruction.numeric.i32.compare;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class I32GtS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        int v2 = vm.popS32();
        int v1 = vm.popS32();
        vm.pushBool(v1 > v2);
    }

}

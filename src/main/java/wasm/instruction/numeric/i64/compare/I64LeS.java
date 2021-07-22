package wasm.instruction.numeric.i64.compare;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class I64LeS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        long v2 = vm.popS64();
        long v1 = vm.popS64();
        vm.pushBool(v1 <= v2);
    }

}

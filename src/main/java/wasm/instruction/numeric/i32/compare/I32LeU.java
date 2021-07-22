package wasm.instruction.numeric.i32.compare;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U32;

public class I32LeU implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        U32 v2 = vm.popU32();
        U32 v1 = vm.popU32();
        vm.pushBool(v1.compareTo(v2) <= 0);
    }

}

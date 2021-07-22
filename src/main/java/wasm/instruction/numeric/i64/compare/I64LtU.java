package wasm.instruction.numeric.i64.compare;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U64;

public class I64LtU implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        U64 v2 = vm.popU64();
        U64 v1 = vm.popU64();
        vm.pushBool(v1.compareTo(v2) < 0);
    }

}

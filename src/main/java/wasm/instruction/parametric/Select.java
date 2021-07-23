package wasm.instruction.parametric;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U64;

public class Select implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        boolean v3 = vm.operandStack.popBool();
        U64 v2 = vm.operandStack.popU64();
        U64 v1 = vm.operandStack.popU64();
        if (v3) {
            vm.operandStack.pushU64(v1);
        } else {
            vm.operandStack.pushU64(v2);
        }
    }

}

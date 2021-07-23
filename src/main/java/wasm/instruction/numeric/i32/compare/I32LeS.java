package wasm.instruction.numeric.i32.compare;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class I32LeS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        int v2 = vm.operandStack.popS32();
        int v1 = vm.operandStack.popS32();
        vm.operandStack.pushBool(v1 <= v2);
    }

}

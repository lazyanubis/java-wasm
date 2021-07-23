package wasm.instruction.numeric.i32.compare;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class I32Eqz implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        vm.operandStack.pushBool(vm.operandStack.popU32().intValue() == 0);
    }

}

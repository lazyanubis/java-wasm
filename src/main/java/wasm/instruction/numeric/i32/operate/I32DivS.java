package wasm.instruction.numeric.i32.operate;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.util.NumberUtil;

public class I32DivS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        int v2 = vm.operandStack.popS32();
        int v1 = vm.operandStack.popS32();
        vm.operandStack.pushS32(NumberUtil.divS(v1, v2));
    }

}

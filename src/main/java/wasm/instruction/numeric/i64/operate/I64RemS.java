package wasm.instruction.numeric.i64.operate;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.util.NumberUtil;

public class I64RemS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        long v2 = vm.operandStack.popS64();
        long v1 = vm.operandStack.popS64();
        vm.operandStack.pushS64(NumberUtil.remS(v1, v2));
    }

}

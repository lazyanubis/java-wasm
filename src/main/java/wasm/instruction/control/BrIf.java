package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Instruction;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class BrIf implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLabelIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        if (vm.operandStack.popBool()) {
            // 如果需要跳转
            Instruction.BR.operate(vm, args);
        }
    }
}

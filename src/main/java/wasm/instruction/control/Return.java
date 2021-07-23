package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Instruction;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.LabelIndex;
import wasm.model.number.U32;

public class Return implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        int[] index = new int[1];

        vm.controlStack.topCallFrame(index);

        Instruction.BR.operate(vm, new LabelIndex(new U32(index[0])));
    }
}

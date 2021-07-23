package wasm.instruction.control;

import wasm.core.ControlFrame;
import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Instruction;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.LabelIndex;

public class Br implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLabelIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof LabelIndex;

        int index = ((LabelIndex) args).intValue();

        for (int i = 0; i < index; i++) {
            vm.controlStack.pop();
        }

        ControlFrame frame = vm.controlStack.top();

        if (frame.instruction != Instruction.LOOP) {
            vm.exitBlock();
        } else {
            vm.resetBlock(frame);
            frame.pc = 0;
        }
    }
}

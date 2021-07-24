package wasm.instruction2.control;

import wasm.core2.structure.ControlFrame;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Instruction;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.model.index.LabelIndex;

public class Br implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLabelIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof LabelIndex;

        int index = ((LabelIndex) args).intValue();

        for (int i = 0; i < index; i++) {
            mi.popFrame();
        }

        ControlFrame frame = mi.topFrame();

        if (frame.instruction != Instruction.LOOP) {
            mi.exitBlock();
        } else {
            mi.resetBlock(frame);
            frame.pc = 0;
        }
    }
}

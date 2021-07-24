package wasm.instruction2.control;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Instruction;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;

public class BrIf implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLabelIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        if (mi.popBool()) {
            // 如果需要跳转
            Instruction.BR.operate(mi, args);
        }
    }
}

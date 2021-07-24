package wasm.instruction2.control;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Instruction;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;

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

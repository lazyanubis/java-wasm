package wasm.instruction2.control;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Instruction;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.model.index.LabelIndex;
import wasm.core.numeric.U32;

public class Return implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        int[] index = new int[1];

        mi.topCallFrame(index);

        Instruction.BR.operate(mi, new LabelIndex(U32.valueOf(index[0])));
    }
}

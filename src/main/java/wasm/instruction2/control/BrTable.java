package wasm.instruction2.control;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Instruction;
import wasm.core2.instruction.Operate;
import wasm.instruction2.dump.DumpBrTable;
import wasm.core2.model.Dump;

public class BrTable implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpBrTable(reader.readLabelIndices(), reader.readLabelIndex());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof DumpBrTable;

        DumpBrTable t = (DumpBrTable) args;

        int n = mi.popU32().intValue();

        if (n < t.labelIndices.length) {
            Instruction.BR.operate(mi, t.labelIndices[n]);
        } else {
            Instruction.BR.operate(mi, t.omit);
        }

    }
}

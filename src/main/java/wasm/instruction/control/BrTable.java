package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Instruction;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpBrTable;
import wasm.model.Dump;

public class BrTable implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpBrTable(reader.readLabelIndices(), reader.readLabelIndex());
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof DumpBrTable;

        DumpBrTable t = (DumpBrTable) args;

        int n = vm.operandStack.popU32().intValue();

        if (n < t.labelIndices.length) {
            Instruction.BR.operate(vm, t.labelIndices[n]);
        } else {
            Instruction.BR.operate(vm, t.omit);
        }

    }
}

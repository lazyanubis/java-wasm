package wasm.instruction.control;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpBrTable;
import wasm.model.Dump;

public class BrTable implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpBrTable(reader.readLabelIndices(), reader.readLabelIndex());
    }



}

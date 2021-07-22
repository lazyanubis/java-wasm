package wasm.instruction.control;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class BrIf implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLabelIndex();
    }



}

package wasm.instruction.memory;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class DataDrop implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readDataIndex();
    }

}

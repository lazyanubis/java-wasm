package wasm.instruction2.memory;

import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;

public class DataDrop implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readDataIndex();
    }

}

package wasm.instruction2.memory;

import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;

public class DataDrop implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readDataIndex();
    }

}

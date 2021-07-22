package wasm.instruction.memory;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpMemory;
import wasm.model.Dump;

public class I64Load16U implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

}

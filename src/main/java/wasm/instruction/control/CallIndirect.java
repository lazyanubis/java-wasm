package wasm.instruction.control;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpCallIndirect;
import wasm.model.Dump;

public class CallIndirect implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpCallIndirect(reader.readTypeIndex(), reader.readTableIndex());
    }

}

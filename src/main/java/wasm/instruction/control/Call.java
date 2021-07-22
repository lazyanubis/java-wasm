package wasm.instruction.control;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class Call implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readFunctionIndex();
    }

}

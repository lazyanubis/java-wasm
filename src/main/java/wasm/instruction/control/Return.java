package wasm.instruction.control;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class Return implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

}

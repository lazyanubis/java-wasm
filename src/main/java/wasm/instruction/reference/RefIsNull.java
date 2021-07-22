package wasm.instruction.reference;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class RefIsNull implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

}

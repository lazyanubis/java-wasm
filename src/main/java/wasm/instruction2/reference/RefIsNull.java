package wasm.instruction2.reference;

import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;

public class RefIsNull implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

}

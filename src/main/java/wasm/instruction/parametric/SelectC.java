package wasm.instruction.parametric;

import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.type.Types;
import wasm.model.Dump;

public class SelectC implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new Types(reader.readValueTypes());
    }

}

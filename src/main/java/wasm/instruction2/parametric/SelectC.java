package wasm.instruction2.parametric;

import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.type.Types;
import wasm.core.model.Dump;

public class SelectC implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new Types(reader.readValueTypes());
    }

}

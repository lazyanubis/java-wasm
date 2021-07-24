package wasm.instruction2.parametric;

import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.type.Types;
import wasm.core2.model.Dump;

public class SelectC implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new Types(reader.readValueTypes());
    }

}

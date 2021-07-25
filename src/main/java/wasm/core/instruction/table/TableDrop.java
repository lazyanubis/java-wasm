package wasm.core.instruction.table;

import wasm.core.exception.Check;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.model.index.ElementIndex;

public class TableDrop implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readElementIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, ElementIndex.class);

        ElementIndex a = (ElementIndex) args;

        Operate.super.operate(mi, args);
    }

}

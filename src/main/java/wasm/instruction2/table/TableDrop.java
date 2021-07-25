package wasm.instruction2.table;

import wasm.core.exception.Check;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core3.model.index.ElementIndex;

import java.util.Objects;

public class TableDrop implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readElementIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Objects.requireNonNull(args);
        Check.require(args, ElementIndex.class);

        ElementIndex a = (ElementIndex) args;

        Operate.super.operate(mi, args);
    }

}

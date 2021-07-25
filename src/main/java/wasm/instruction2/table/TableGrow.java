package wasm.instruction2.table;

import wasm.core.exception.Check;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core3.model.index.TableIndex;

import java.util.Objects;

public class TableGrow implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readTableIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, TableIndex.class);

        TableIndex a = (TableIndex) args;

        Operate.super.operate(mi, args);
    }

}

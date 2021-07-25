package wasm.instruction2.table;

import wasm.core.exception.Check;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.instruction2.dump.DumpTableCopy;

import java.util.Objects;

public class TableCopy implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpTableCopy(reader.readTableIndex(), reader.readTableIndex());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, DumpTableCopy.class);

        DumpTableCopy a = (DumpTableCopy) args;

        Operate.super.operate(mi, args);
    }

}

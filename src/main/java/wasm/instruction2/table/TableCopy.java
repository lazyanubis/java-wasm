package wasm.instruction2.table;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.instruction2.dump.DumpTableCopy;
import wasm.core.model.Dump;

public class TableCopy implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpTableCopy(reader.readTableIndex(), reader.readTableIndex());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof DumpTableCopy;

        DumpTableCopy a = (DumpTableCopy) args;

        Operate.super.operate(mi, args);
    }

}

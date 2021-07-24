package wasm.instruction2.table;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.instruction2.dump.DumpTableCopy;
import wasm.core2.model.Dump;

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

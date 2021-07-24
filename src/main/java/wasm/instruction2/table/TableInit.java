package wasm.instruction2.table;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.instruction2.dump.DumpTableInit;
import wasm.core.model.Dump;

public class TableInit implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpTableInit(reader.readElementIndex(), reader.readTableIndex());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof DumpTableInit;

        DumpTableInit a = (DumpTableInit) args;

        Operate.super.operate(mi, args);
    }

}

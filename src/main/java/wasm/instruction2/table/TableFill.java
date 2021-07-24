package wasm.instruction2.table;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.index.TableIndex;

public class TableFill implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readTableIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof TableIndex;

        TableIndex a = (TableIndex) args;

        Operate.super.operate(mi, args);
    }

}

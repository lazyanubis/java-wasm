package wasm.instruction2.table;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core.model.index.TableIndex;

public class TableSet implements Operate {

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

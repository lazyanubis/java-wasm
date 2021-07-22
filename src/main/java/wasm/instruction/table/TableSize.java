package wasm.instruction.table;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.TableIndex;

public class TableSize implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readTableIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof TableIndex;

        TableIndex a = (TableIndex) args;

        Operate.super.operate(vm, args);
    }

}

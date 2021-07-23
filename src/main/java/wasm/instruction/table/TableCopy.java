package wasm.instruction.table;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpTableCopy;
import wasm.model.Dump;

public class TableCopy implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpTableCopy(reader.readTableIndex(), reader.readTableIndex());
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;
        assert args instanceof DumpTableCopy;

        DumpTableCopy a = (DumpTableCopy) args;

        Operate.super.operate(vm, args);
    }

}

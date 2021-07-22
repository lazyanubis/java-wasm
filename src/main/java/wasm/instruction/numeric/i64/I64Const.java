package wasm.instruction.numeric.i64;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpI64;
import wasm.model.Dump;

public class I64Const implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpI64(reader.readLeb128S64());
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof DumpI64;

        DumpI64 a = (DumpI64) args;

        vm.pushS64(a.value);
    }

}

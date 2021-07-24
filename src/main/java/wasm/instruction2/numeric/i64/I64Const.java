package wasm.instruction2.numeric.i64;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.instruction2.dump.DumpI64;
import wasm.core.model.Dump;

public class I64Const implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpI64(reader.readLeb128S64());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof DumpI64;

        DumpI64 a = (DumpI64) args;

        mi.pushS64(a.value);
    }

}

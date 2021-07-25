package wasm.core.instruction.numeric.i64;

import wasm.core.exception.Check;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.dump.DumpI64;

public class I64Const implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpI64(reader.readLeb128S64());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, DumpI64.class);

        DumpI64 a = (DumpI64) args;

        mi.pushS64(a.value);
    }

}

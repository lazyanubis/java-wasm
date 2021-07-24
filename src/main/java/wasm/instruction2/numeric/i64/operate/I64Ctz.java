package wasm.instruction2.numeric.i64.operate;

import wasm.core.model.Dump;
import wasm.core.numeric.U64;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;

public class I64Ctz implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        U64 v = mi.popU64();
        mi.pushU64(new U64(v.ctz()));
    }

}

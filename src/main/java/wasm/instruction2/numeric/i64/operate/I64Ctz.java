package wasm.instruction2.numeric.i64.operate;

import wasm.core2.model.Dump;
import wasm.core2.numeric.U64;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;

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

package wasm.instruction2.numeric.i64.compare;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.numeric.U64;

public class I64LtU implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        U64 v2 = mi.popU64();
        U64 v1 = mi.popU64();
        mi.pushBool(v1.compareTo(v2) < 0);
    }

}

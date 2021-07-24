package wasm.instruction2.parametric;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core3.numeric.U64;

public class Select implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        boolean v3 = mi.popBool();
        U64 v2 = mi.popU64();
        U64 v1 = mi.popU64();
        if (v3) {
            mi.pushU64(v1);
        } else {
            mi.pushU64(v2);
        }
    }

}

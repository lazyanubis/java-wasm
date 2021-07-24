package wasm.instruction2.numeric.i64.compare;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;

public class I64Eqz implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        mi.pushBool(mi.popU64().longValue() == 0);
    }

}

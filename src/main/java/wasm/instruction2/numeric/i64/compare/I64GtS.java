package wasm.instruction2.numeric.i64.compare;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;

public class I64GtS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        long v2 = mi.popS64();
        long v1 = mi.popS64();
        mi.pushBool(v1 > v2);
    }

}

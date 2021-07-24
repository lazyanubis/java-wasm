package wasm.instruction2.numeric.i32.compare;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;

public class I32LtS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        int v2 = mi.popS32();
        int v1 = mi.popS32();
        mi.pushBool(v1 < v2);
    }

}

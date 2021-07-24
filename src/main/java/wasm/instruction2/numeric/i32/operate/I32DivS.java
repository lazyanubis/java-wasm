package wasm.instruction2.numeric.i32.operate;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.util.NumberUtil;

public class I32DivS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        int v2 = mi.popS32();
        int v1 = mi.popS32();
        mi.pushS32(NumberUtil.divS(v1, v2));
    }

}

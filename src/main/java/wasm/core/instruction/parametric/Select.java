package wasm.core.instruction.parametric;

import wasm.core.numeric.USize;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;

public class Select implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        boolean v3 = mi.popBool();
        USize v2 = mi.popUSize();
        USize v1 = mi.popUSize();
        if (v3) {
            mi.pushUSize(v1);
        } else {
            mi.pushUSize(v2);
        }
    }

}
